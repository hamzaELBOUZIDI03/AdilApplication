package com.adil.app.service;

import com.adil.app.Enumeration.CoffreNameEnum;
import com.adil.app.dto.AdilDTO;
import com.adil.app.dto.AdilResponse;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.AdilMapper;
import com.adil.app.repository.AdilRepository;
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
public class AdilService {

    private final AdilMapper adilMapper;
    private final AdilRepository adilRepository;
    private final CoffreFortRepository coffreFortRepository;

    public AdilDTO create(AdilDTO adilDTO) {
        log.info("Start service : Creating adil Record coffre with details: {}", adilDTO);

        if (adilDTO.getId() != null && adilRepository.existsById(adilDTO.getId())) {
            throw new FunctionalException("Record with ID " + adilDTO.getId() + " already exists");
        }

        var mappedAdil = adilMapper.toAdil(adilDTO);

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.ADIL_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.ADIL_NAME_TABLE.getCoffreName()));
        coffreFort.setDateDerniereModification(LocalDate.now());
        mappedAdil.setCoffreFort(coffreFort);
        var savedAdil = adilRepository.save(mappedAdil);
        var result = adilMapper.toAdilDTO(savedAdil);
        log.info("End service : Created adil Record coffre with details: {}", result);
        return result;
    }

    public AdilDTO update(Integer adilId, AdilDTO adilDTO) {
        log.info("Start service : Updating adil Record coffre with ID: {} and details: {}", adilId, adilDTO);
        var existingAdil = adilRepository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));
        adilMapper.updateEntity(existingAdil, adilDTO);
        if (existingAdil.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort name must not be null");
        }
        existingAdil.getCoffreFort().setDateDerniereModification(LocalDate.now());
        var updatedAdil = adilRepository.save(existingAdil);
        var result = adilMapper.toAdilDTO(updatedAdil);
        log.info("End service : Updated adil Record coffre with details: {}", result);
        return result;
    }

    public AdilDTO getById(Integer adilId) {
        log.info("Start service : Retrieving adil Record coffre with ID: {}", adilId);
        var adil = adilRepository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));
        var result = adilMapper.toAdilDTO(adil);
        log.info("End service : Retrieved adil Record coffre with details: {}", result);
        return result;
    }

    public void delete(Integer adilId) {
        log.info("Start service : Deleting adil Record coffre with ID: {}", adilId);
        var existingAdil = adilRepository.findById(adilId)
                .orElseThrow(() -> new FunctionalException("Adil coffre not found with ID: " + adilId));

        if (existingAdil.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort associated with adil coffre must not be null");
        }

        var existingCoffreFort = existingAdil.getCoffreFort();
        existingCoffreFort.setDateDerniereModification(LocalDate.now());
        coffreFortRepository.save(existingCoffreFort);
        adilRepository.deleteById(existingAdil.getId());
        log.info("End service : Deleted adil Record coffre with ID: {}", existingAdil.getId());
    }

    public AdilResponse getAll(int page, int size) {
        log.info("Start service : Retrieving all adils Records coffre with pagination: page={}, size={}", page, size);

        Page<AdilDTO> adils = adilRepository.findAllSortedByDateRetourThenId(PageRequest.of(page, size))
                .map(adilMapper::toAdilDTO);

        Double totalMontant = adilRepository.getTotalMontant();

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.ADIL_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.ADIL_NAME_TABLE.getCoffreName()));

        Double coffreTotal = Optional.ofNullable(coffreFort.getCoffreTotal())
                .orElseThrow(() -> new FunctionalException("CoffreFort total is not set"));

        LocalDate lastDateModification = Optional.ofNullable(coffreFort.getDateDerniereModification())
                .orElseThrow(() -> new FunctionalException("CoffreFort last modification date is not set"));

        Double rest = coffreTotal - totalMontant;

        var result = AdilResponse.builder()
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

    public List<AdilDTO> searchByNomOrMontant(String nom, Double montant) {
        log.info("Start service : Searching adil records by name: {} or montant: {}", nom, montant);
        var adils = adilRepository.searchByNomOrMontant(nom, montant);

        if (CollectionUtils.isEmpty(adils)) {
            log.warn("No adil record found with name: {} or montant: {}", nom, montant);
            return List.of();
        }

        var result = adils.stream().map(adilMapper::toAdilDTO).toList();
        log.info("End service : Found adil records");
        return result;
    }


}
