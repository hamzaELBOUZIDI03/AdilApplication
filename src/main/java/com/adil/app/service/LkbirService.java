package com.adil.app.service;

import com.adil.app.Enumeration.CoffreNameEnum;
import com.adil.app.dto.LkbirDTO;
import com.adil.app.dto.LkbirResponse;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.LkbirMapper;
import com.adil.app.repository.CoffreFortRepository;
import com.adil.app.repository.LkbirRepository;
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
public class LkbirService {

    private final LkbirMapper lkbirMapper;
    private final LkbirRepository lkbirRepository;
    private final CoffreFortRepository coffreFortRepository;

    public LkbirDTO create(LkbirDTO lkbirDTO) {
        log.info("Start service : Creating lkbir Record with details: {}", lkbirDTO);

        if (lkbirDTO.getId() != null && lkbirRepository.existsById(lkbirDTO.getId())) {
            throw new FunctionalException("Record with ID " + lkbirDTO.getId() + " already exists");
        }

        var mappedLkbir = lkbirMapper.toLkbir(lkbirDTO);

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.LKBIR_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.LKBIR_NAME_TABLE.getCoffreName()));
        coffreFort.setDateDerniereModification(LocalDate.now());
        mappedLkbir.setCoffreFort(coffreFort);
        var savedLkbir = lkbirRepository.save(mappedLkbir);
        var result = lkbirMapper.toLkbirDTO(savedLkbir);
        log.info("End service : Created lkbir Record with details: {}", result);
        return result;
    }

    public LkbirDTO update(Integer lkbirId, LkbirDTO lkbirDTO) {
        log.info("Start service : Updating lkbir Record with ID: {} and details: {}", lkbirId, lkbirDTO);
        var existingLkbir = lkbirRepository.findById(lkbirId)
                .orElseThrow(() -> new FunctionalException("Lkbir not found with ID: " + lkbirId));
        lkbirMapper.updateEntity(existingLkbir, lkbirDTO);
        if (existingLkbir.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort name must not be null");
        }
        existingLkbir.getCoffreFort().setDateDerniereModification(LocalDate.now());
        var updatedLkbir = lkbirRepository.save(existingLkbir);
        var result = lkbirMapper.toLkbirDTO(updatedLkbir);
        log.info("End service : Updated lkbir Record with details: {}", result);
        return result;
    }

    public LkbirDTO getById(Integer lkbirId) {
        log.info("Start service : Retrieving lkbir with ID: {}", lkbirId);
        var lkbir = lkbirRepository.findById(lkbirId)
                .orElseThrow(() -> new FunctionalException("Lkbir not found with ID: " + lkbirId));
        var result = lkbirMapper.toLkbirDTO(lkbir);
        log.info("End service : Retrieved lkbir with details: {}", result);
        return result;
    }

    public void delete(Integer lkbirId) {
        log.info("Start service : Deleting lkbir Record with ID: {}", lkbirId);
        var existingLkbir = lkbirRepository.findById(lkbirId)
                .orElseThrow(() -> new FunctionalException("Lkbir Record not found with ID: " + lkbirId));

        if (existingLkbir.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort associated with lkbir must not be null");
        }

        var existingCoffreFort = existingLkbir.getCoffreFort();
        existingCoffreFort.setDateDerniereModification(LocalDate.now());
        coffreFortRepository.save(existingCoffreFort);
        lkbirRepository.deleteById(existingLkbir.getId());
        log.info("End service : Deleted lkbir Record with ID: {}", existingLkbir.getId());
    }

    public LkbirResponse getAll(int page, int size) {
        log.info("Start service : Retrieving all Lkbirs Record with pagination: page={}, size={}", page, size);
        Page<LkbirDTO> lkbirs = lkbirRepository.findAll(PageRequest.of(page, size))
                .map(lkbirMapper::toLkbirDTO);

        Double totalMontant = lkbirs.stream()
                .mapToDouble(dto -> Optional.ofNullable(dto.getMontant()).orElse(0.0))
                .sum();

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.LKBIR_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.LKBIR_NAME_TABLE.getCoffreName()));

        LocalDate lastDateModification = Optional.ofNullable(coffreFort.getDateDerniereModification())
                .orElseThrow(() -> new FunctionalException("CoffreFort last modification date is not set"));

        Double coffreTotal = Optional.ofNullable(coffreFort.getCoffreTotal()).orElse(0.0);

        Double rest = coffreTotal - totalMontant;

        var result = LkbirResponse.builder()
                .elements(lkbirs.getContent())
                .rest(rest)
                .montantConsommee(totalMontant)
                .lastDateModification(lastDateModification)
                .coffreTotal(coffreTotal)
                .totalPages(lkbirs.getTotalPages())
                .totalElements(lkbirs.getTotalElements())
                .size(lkbirs.getSize())
                .number(lkbirs.getNumber())
                .first(lkbirs.isFirst())
                .last(lkbirs.isLast())
                .numberOfElements(lkbirs.getNumberOfElements())
                .build();
        log.info("End service : Retrieved all Lkbirs Records with total rest: {}", result.getRest());
        return result;
    }

    public List<LkbirDTO> searchByNomOrMontant(String nom, Double montant) {
        log.info("Start service : Searching lkbir records by name: {} or montant: {}", nom, montant);
        var lkbirs = lkbirRepository.searchByNomOrMontant(nom, montant);

        if (CollectionUtils.isEmpty(lkbirs)) {
            log.warn("No lkbir record found with name: {} or montant: {}", nom, montant);
            return List.of();
        }

        var result = lkbirs.stream().map(lkbirMapper::toLkbirDTO).toList();
        log.info("End service : Found lkbir records");
        return result;
    }


}
