package com.adil.app.service;

import com.adil.app.Enumeration.CoffreNameEnum;
import com.adil.app.dto.Adil3DTO;
import com.adil.app.dto.Adil3Response;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.Adil3Mapper;
import com.adil.app.repository.Adil3Repository;
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
public class Adil3Service {

    private final Adil3Mapper adil3Mapper;
    private final Adil3Repository adil3Repository;
    private final CoffreFortRepository coffreFortRepository;

    public Adil3DTO create(Adil3DTO adilDTO) {
        log.info("Start service : Creating adil3 Record coffre with details: {}", adilDTO);

        if (adilDTO.getId() != null && adil3Repository.existsById(adilDTO.getId())) {
            throw new FunctionalException("Record with ID " + adilDTO.getId() + " already exists");
        }

        var mappedAdil = adil3Mapper.toAdil3(adilDTO);

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.ADIL3_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.ADIL3_NAME_TABLE.getCoffreName()));
        coffreFort.setDateDerniereModification(LocalDate.now());
        mappedAdil.setCoffreFort(coffreFort);
        var savedAdil = adil3Repository.save(mappedAdil);
        var result = adil3Mapper.toAdil3DTO(savedAdil);
        log.info("End service : Created adil3 Record coffre with details: {}", result);
        return result;
    }

    public Adil3DTO update(Integer adilId, Adil3DTO adilDTO) {
        log.info("Start service : Updating adil3 Record coffre with ID: {} and details: {}", adilId, adilDTO);
        var existingAdil = adil3Repository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));
        adil3Mapper.updateEntity(existingAdil, adilDTO);
        if (existingAdil.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort name must not be null");
        }
        existingAdil.getCoffreFort().setDateDerniereModification(LocalDate.now());
        var updatedAdil = adil3Repository.save(existingAdil);
        var result = adil3Mapper.toAdil3DTO(updatedAdil);
        log.info("End service : Updated adil3 Record coffre with details: {}", result);
        return result;
    }

    public Adil3DTO getById(Integer adilId) {
        log.info("Start service : Retrieving adil3 Record coffre with ID: {}", adilId);
        var adil3 = adil3Repository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));
        var result = adil3Mapper.toAdil3DTO(adil3);
        log.info("End service : Retrieved adil3 Record coffre with details: {}", result);
        return result;
    }

    public void delete(Integer adilId) {
        log.info("Start service : Deleting adil3 Record coffre with ID: {}", adilId);
        var existingAdil = adil3Repository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));

        if (existingAdil.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort associated with adil3 coffre must not be null");
        }

        var existingCoffreFort = existingAdil.getCoffreFort();
        existingCoffreFort.setDateDerniereModification(LocalDate.now());
        coffreFortRepository.save(existingCoffreFort);
        adil3Repository.deleteById(existingAdil.getId());
        log.info("End service : Deleted adil3 Record coffre with ID: {}", existingAdil.getId());
    }

    public Adil3Response getAll(int page, int size) {
        log.info("Start service : Retrieving all adils Records coffre with pagination: page={}, size={}", page, size);

        Page<Adil3DTO> adils = adil3Repository.findAllSortedByDateRetourThenId(PageRequest.of(page, size))
                .map(adil3Mapper::toAdil3DTO);

        Double totalMontant = adil3Repository.getTotalMontant();

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.ADIL3_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.ADIL3_NAME_TABLE.getCoffreName()));

        Double coffreTotal = Optional.ofNullable(coffreFort.getCoffreTotal())
                .orElseThrow(() -> new FunctionalException("CoffreFort total is not set"));

        LocalDate lastDateModification = Optional.ofNullable(coffreFort.getDateDerniereModification())
                .orElseThrow(() -> new FunctionalException("CoffreFort last modification date is not set"));

        Double rest = coffreTotal - totalMontant;

        var result = Adil3Response.builder()
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

    public List<Adil3DTO> searchByNomOrMontant(String nom, Double montant) {
        log.info("Start service : Searching adil3 records by name: {} or montant: {}", nom, montant);
        var adils = adil3Repository.searchByNomOrMontant(nom, montant);

        if (CollectionUtils.isEmpty(adils)) {
            log.warn("No adil3 record found with name: {} or montant: {}", nom, montant);
            return List.of();
        }

        var result = adils.stream().map(adil3Mapper::toAdil3DTO).toList();
        log.info("End service : Found adil3 records");
        return result;
    }


}
