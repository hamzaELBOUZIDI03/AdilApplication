package com.adil.app.service;

import com.adil.app.Enumeration.CoffreNameEnum;
import com.adil.app.dto.CreditDTO;
import com.adil.app.dto.CreditResponse;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.CreditMapper;
import com.adil.app.repository.CoffreFortRepository;
import com.adil.app.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditService {

    private final CreditMapper creditMapper;
    private final CreditRepository creditRepository;
    private final CoffreFortRepository coffreFortRepository;

    public CreditDTO create(CreditDTO creditDTO) {
        log.info("Start service : Creating credit Record with details: {}", creditDTO);

        if (creditDTO.getId() != null && creditRepository.existsById(creditDTO.getId())) {
            throw new FunctionalException("Record with ID " + creditDTO.getId() + " already exists");
        }

        var mappedCredit = creditMapper.toCredit(creditDTO);

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.CREDIT_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.CREDIT_NAME_TABLE.getCoffreName()));
        coffreFort.setDateDerniereModification(LocalDate.now());
        mappedCredit.setCoffreFort(coffreFort);
        var savedCredit = creditRepository.save(mappedCredit);
        var result = creditMapper.toCreditDTO(savedCredit);
        log.info("End service : Created credit with details: {}", mappedCredit);
        return result;
    }

    public CreditDTO update(Integer creditId, CreditDTO creditDTO) {
        log.info("Start service : Updating credit Record with ID: {} and details: {}", creditId, creditDTO);
        var existingCredit = creditRepository.findById(creditId)
                .orElseThrow(() -> new FunctionalException("Credit not found with ID: " + creditId));
        creditMapper.updateEntity(existingCredit, creditDTO);
        if (existingCredit.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort name must not be null");
        }
        existingCredit.getCoffreFort().setDateDerniereModification(LocalDate.now());
        var updatedCredit = creditRepository.save(existingCredit);
        var result = creditMapper.toCreditDTO(updatedCredit);
        log.info("End service : Updated credit Record with details: {}", result);
        return result;
    }

    public CreditDTO getById(Integer creditId) {
        log.info("Start service : Retrieving credit Record with ID: {}", creditId);
        var credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new FunctionalException("Credit not found with ID: " + creditId));
        var result = creditMapper.toCreditDTO(credit);
        log.info("End service : Retrieved credit Record with details: {}", result);
        return result;
    }

    public void delete(Integer creditId) {
        log.info("Start service : Deleting credit Record with ID: {}", creditId);
        var existingCredit = creditRepository.findById(creditId)
                .orElseThrow(() -> new FunctionalException("Credit not found with ID: " + creditId));

        if (existingCredit.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort associated with credit must not be null");
        }

        var existingCoffreFort = existingCredit.getCoffreFort();
        existingCoffreFort.setDateDerniereModification(LocalDate.now());
        coffreFortRepository.save(existingCoffreFort);
        creditRepository.deleteById(existingCredit.getId());
        log.info("End service : Deleted credit Record with ID: {}", existingCredit.getId());
    }

    public CreditResponse getAll(int page, int size) {
        log.info("Start service : Retrieving all credits Records with pagination: page={}, size={}", page, size);
        Page<CreditDTO> credits = creditRepository.findAll(PageRequest.of(page, size))
                .map(creditMapper::toCreditDTO);

        Double totalMontant = credits.stream()
                .mapToDouble(dto -> Optional.ofNullable(dto.getMontant()).orElse(0.0))
                .sum();

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.CREDIT_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.CREDIT_NAME_TABLE.getCoffreName()));

        var lastDateModification = Optional.ofNullable(coffreFort.getDateDerniereModification())
                .orElseThrow(() -> new FunctionalException("CoffreFort last modification date is not set"));

        Double coffreTotal = Optional.ofNullable(coffreFort.getCoffreTotal())
                .orElseThrow(() -> new FunctionalException("CoffreFort total is not set"));

        Double rest = coffreTotal - totalMontant;

        var result = CreditResponse.builder()
                .elements(credits.getContent())
                .rest(rest)
                .montantConsommee(totalMontant)
                .lastDateModification(lastDateModification)
                .coffreTotal(coffreTotal)
                .totalPages(credits.getTotalPages())
                .totalElements(credits.getTotalElements())
                .size(credits.getSize())
                .number(credits.getNumber())
                .first(credits.isFirst())
                .last(credits.isLast())
                .numberOfElements(credits.getNumberOfElements())
                .build();
        log.info("End service : Retrieved all credits Records with total rest: {}", result.getRest());
        return result;
    }

    public List<CreditDTO> searchByNomOrMontant(String nom, Double montant) {
        log.info("Start service : Searching credit by name: {} or montant: {}", nom, montant);
        var credits = creditRepository.searchByNomOrMontant(nom, montant);
        if (CollectionUtils.isEmpty(credits)) {
            log.warn("No credit found with name: {} or montant: {}", nom, montant);
            return List.of();
        }
        var result = credits.stream().map(creditMapper::toCreditDTO).toList();
        log.info("End service : Found credit records");
        return result;
    }

}
