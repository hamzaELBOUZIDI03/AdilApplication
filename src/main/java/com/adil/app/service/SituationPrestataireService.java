package com.adil.app.service;

import com.adil.app.domain.SituationPrestataire;
import com.adil.app.dto.SituationPrestataireDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.SituationPrestataireMapper;
import com.adil.app.repository.SituationPrestataireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SituationPrestataireService {

    private final SituationPrestataireRepository situationPrestataireRepository;
    private final SituationPrestataireMapper situationPrestataireMapper;
    
    public SituationPrestataireDTO save(SituationPrestataireDTO situationPrestataireDTO) {
        log.info("Start service : Saving SituationPrestataire with details: {}", situationPrestataireDTO);
        SituationPrestataire situationPrestataire = situationPrestataireMapper.toSituationPrestataire(situationPrestataireDTO);
        situationPrestataire = situationPrestataireRepository.save(situationPrestataire);
        var result = situationPrestataireMapper.toSituationPrestataireDTO(situationPrestataire);
        log.info("End service : Saving SituationPrestataire with details: {}", result);
        return result;
    }
    
    public SituationPrestataireDTO update(Integer id, SituationPrestataireDTO situationPrestataireDTO) {
        log.info("Start service : Updating SituationPrestataire with details: {}", situationPrestataireDTO);
        var existSituationPrestataire = situationPrestataireRepository.findById(id).
                orElseThrow(() -> new FunctionalException("SituationPrestataire not found"));
        situationPrestataireMapper.updatedEntity(existSituationPrestataire, situationPrestataireDTO);
        var savedSituationPrestataire = situationPrestataireRepository.save(existSituationPrestataire);
        var result = situationPrestataireMapper.toSituationPrestataireDTO(savedSituationPrestataire);
        log.info("End service : Updating SituationPrestataire with details: {}", result);
        return result;
    }
    
    public SituationPrestataireDTO findById(Integer id) {
        log.info("Start service : Finding SituationPrestataire with id: {}", id);
        var situationPrestataire = situationPrestataireRepository.findById(id).
                orElseThrow(() -> new FunctionalException("SituationPrestataire not found"));
        var result = situationPrestataireMapper.toSituationPrestataireDTO(situationPrestataire);
        log.info("End service : Finding SituationPrestataire with details: {}", result);
        return result;
    }

    public void delete(Integer id) {
        var situationPrestataire = situationPrestataireRepository.existsById(id);
        if (situationPrestataire) {
            log.info("Start service : Deleting SituationPrestataire with id: {}", id);
            situationPrestataireRepository.deleteById(id);
            log.info("End service : Deleted SituationPrestataire with id: {}", id);
        } else {
            log.warn("SituationPrestataire with id {} not found for deletion", id);
            throw new FunctionalException("SituationPrestataire not found for deletion");
        }
    }

    public Page<SituationPrestataireDTO> findAll(int page, int size) {
        log.info("Start service : Finding all SituationPrestataires");
        Pageable pageable = PageRequest.of(page, size);
        Page<SituationPrestataire> situationPrestataires = situationPrestataireRepository.findAllByOrderByDateEcrireAsc(pageable);
        Page<SituationPrestataireDTO> result = situationPrestataires.map(situationPrestataireMapper::toSituationPrestataireDTO);
        log.info("End service : Found {} SituationPrestataires", result.getTotalElements());
        return result;
    }
}