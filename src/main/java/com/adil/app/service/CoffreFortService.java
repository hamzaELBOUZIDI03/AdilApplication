package com.adil.app.service;

import com.adil.app.dto.CoffreFortDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.CoffreFortMapper;
import com.adil.app.repository.CoffreFortRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoffreFortService {

    private final CoffreFortRepository coffreFortRepository;
    private final CoffreFortMapper coffreFortMapper;

    public CoffreFortDTO update(Integer id, CoffreFortDTO coffreFortDTO) {
        log.info("Start service : Updating CoffreFort with ID: {} and details: {}", id, coffreFortDTO);

        var existingCoffreFort = coffreFortRepository.findById(id)
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with ID: " + id));

        coffreFortMapper.updateEntity(existingCoffreFort, coffreFortDTO);
        existingCoffreFort.setDateDerniereModification(LocalDate.now());
        var updatedCoffreFort = coffreFortRepository.save(existingCoffreFort);

        var result = coffreFortMapper.toCoffreFortDTO(updatedCoffreFort);
        log.info("End service : Updated CoffreFort with details: {}", result);

        return result;
    }

    public List<CoffreFortDTO> getAllCoffreFort() {
        log.info("Start service : Retrieving all CoffreFort");

        var coffres = coffreFortRepository.findAll()
                .stream()
                .map(coffreFortMapper::toCoffreFortDTO)
                .toList();

        log.info("End service : Retrieved all CoffreFort, count: {}", coffres.size());
        return coffres;
    }

    public CoffreFortDTO getById(Integer id) {
        log.info("Start service : Retrieving CoffreFort by ID: {}", id);

        var existingCoffreFort = coffreFortRepository.findById(id)
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with ID: " + id));

        var result = coffreFortMapper.toCoffreFortDTO(existingCoffreFort);
        log.info("End service : Retrieved CoffreFort with details: {}", result);

        return result;
    }


}
