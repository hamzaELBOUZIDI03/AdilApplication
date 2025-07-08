package com.adil.app.service;

import com.adil.app.Enumeration.CoffreNameEnum;
import com.adil.app.dto.Adil2DTO;
import com.adil.app.dto.Adil2Response;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.Adil2Mapper;
import com.adil.app.repository.Adil2Repository;
import com.adil.app.repository.CoffreFortRepository;
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
public class Adil2Service {

    private final Adil2Mapper adil2Mapper;
    private final Adil2Repository adil2Repository;
    private final CoffreFortRepository coffreFortRepository;

    public Adil2DTO create(Adil2DTO adilDTO) {
        log.info("Start service : Creating adil2 Record coffre with details: {}", adilDTO);

        if (adilDTO.getId() != null && adil2Repository.existsById(adilDTO.getId())) {
            throw new FunctionalException("Record with ID " + adilDTO.getId() + " already exists");
        }

        var mappedAdil = adil2Mapper.toAdil2(adilDTO);

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.ADIL2_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.ADIL2_NAME_TABLE.getCoffreName()));
        coffreFort.setDateDerniereModification(LocalDate.now());
        mappedAdil.setCoffreFort(coffreFort);
        var savedAdil = adil2Repository.save(mappedAdil);
        var result = adil2Mapper.toAdil2DTO(savedAdil);
        log.info("End service : Created adil2 Record coffre with details: {}", result);
        return result;
    }

    public Adil2DTO update(Integer adilId, Adil2DTO adilDTO) {
        log.info("Start service : Updating adil2 Record coffre with ID: {} and details: {}", adilId, adilDTO);
        var existingAdil = adil2Repository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));
        adil2Mapper.updateEntity(existingAdil, adilDTO);
        if (existingAdil.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort name must not be null");
        }
        existingAdil.getCoffreFort().setDateDerniereModification(LocalDate.now());
        var updatedAdil = adil2Repository.save(existingAdil);
        var result = adil2Mapper.toAdil2DTO(updatedAdil);
        log.info("End service : Updated adil2 Record coffre with details: {}", result);
        return result;
    }

    public Adil2DTO getById(Integer adilId) {
        log.info("Start service : Retrieving adil2 Record coffre with ID: {}", adilId);
        var adil2 = adil2Repository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));
        var result = adil2Mapper.toAdil2DTO(adil2);
        log.info("End service : Retrieved adil2 Record coffre with details: {}", result);
        return result;
    }

    public void delete(Integer adilId) {
        log.info("Start service : Deleting adil2 Record coffre with ID: {}", adilId);
        var existingAdil = adil2Repository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));

        if (existingAdil.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort associated with adil2 coffre must not be null");
        }

        var existingCoffreFort = existingAdil.getCoffreFort();
        existingCoffreFort.setDateDerniereModification(LocalDate.now());
        coffreFortRepository.save(existingCoffreFort);
        adil2Repository.deleteById(existingAdil.getId());
        log.info("End service : Deleted adil2 Record coffre with ID: {}", existingAdil.getId());
    }

    public Adil2Response getAll(int page, int size) {
        log.info("Start service : Retrieving all adils Records coffre with pagination: page={}, size={}", page, size);

        Page<Adil2DTO> adils = adil2Repository.findAllSortedByDateRetourThenId(PageRequest.of(page, size))
                .map(adil2Mapper::toAdil2DTO);

        Double totalMontant = adil2Repository.getTotalMontant();

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.ADIL2_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.ADIL2_NAME_TABLE.getCoffreName()));

        Double coffreTotal = Optional.ofNullable(coffreFort.getCoffreTotal())
                .orElseThrow(() -> new FunctionalException("CoffreFort total is not set"));

        LocalDate lastDateModification = Optional.ofNullable(coffreFort.getDateDerniereModification())
                .orElseThrow(() -> new FunctionalException("CoffreFort last modification date is not set"));

        Double rest = coffreTotal - totalMontant;

        var result = Adil2Response.builder()
                .elements(adils.getContent())
                .rest(rest)
                .montantConsommee(totalMontant)
                .lastDateModification(lastDateModification)
                .coffreTotal(coffreTotal)
                .totalPages(adils.getTotalPages())
                .totalElements(adils.getTotalElements())
                .size(adils.getSize())
                .number(adils.getNumber())
                .first(adils.isFirst())
                .last(adils.isLast())
                .numberOfElements(adils.getNumberOfElements())
                .build();
        log.info("End service : Retrieved all adils Records coffre with total rest: {}", result.getRest());
        return result;
    }

    public List<Adil2DTO> searchByNomOrMontant(String nom, Double montant) {
        log.info("Start service : Searching adil2 records by name: {} or montant: {}", nom, montant);
        var adils = adil2Repository.searchByNomOrMontant(nom, montant);

        if (CollectionUtils.isEmpty(adils)) {
            log.warn("No adil2 record found with name: {} or montant: {}", nom, montant);
            return List.of();
        }

        var result = adils.stream().map(adil2Mapper::toAdil2DTO).toList();
        log.info("End service : Found adil2 records");
        return result;
    }


}
