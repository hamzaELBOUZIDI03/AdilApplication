package com.adil.app.service;

import com.adil.app.dto.IdentifiantDTO;
import com.adil.app.mapper.IdentifiantMapper;
import com.adil.app.repository.IdentifiantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdentifiantService {

    private final IdentifiantRepository identifiantRepository;
    private final IdentifiantMapper identifiantMapper;

    public IdentifiantDTO save(IdentifiantDTO identifiantDTO) {
        log.info("Start service : Saving Identifiant with details: {}", identifiantDTO);
        var identifiant = identifiantMapper.toIdentifiant(identifiantDTO);
        identifiant = identifiantRepository.save(identifiant);
        var result = identifiantMapper.toIdentifiantDTO(identifiant);
        log.info("End service : Saving Identifiant with details: {}", result);
        return result;
    }

    public IdentifiantDTO update(Integer id, IdentifiantDTO identifiantDTO) {
        log.info("Start service : Updating Identifiant with details: {}", identifiantDTO);
        var existIdentifiant = identifiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identifiant not found"));
        identifiantMapper.updatedEntity(existIdentifiant, identifiantDTO);
        var savedIdentifiant = identifiantRepository.save(existIdentifiant);
        var result = identifiantMapper.toIdentifiantDTO(savedIdentifiant);
        log.info("End service : Updating Identifiant with details: {}", result);
        return result;
    }

    public IdentifiantDTO findById(Integer id) {
        log.info("Start service : Finding Identifiant with id: {}", id);
        var identifiant = identifiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identifiant not found"));
        var result = identifiantMapper.toIdentifiantDTO(identifiant);
        log.info("End service : Finding Identifiant with details: {}", result);
        return result;
    }

    public void delete(Integer id) {
        var identifiantExists = identifiantRepository.existsById(id);
        if (identifiantExists) {
            log.info("Start service : Deleting Identifiant with id: {}", id);
            identifiantRepository.deleteById(id);
            log.info("End service : Deleted Identifiant with id: {}", id);
        } else {
            log.warn("Identifiant with id {} not found for deletion", id);
            throw new RuntimeException("Identifiant not found for deletion");
        }
    }

    public Page<IdentifiantDTO> findAll(int page, int size) {
        log.info("Start service : Finding all Identifiants with ");
        Pageable pageable = PageRequest.of(page, size);
        var allIdentifient = identifiantRepository.findAll(pageable);
        var result = allIdentifient.map(identifiantMapper::toIdentifiantDTO);
        log.info("End service : Found all Identifiants with total elements: {}", result.getTotalElements());
        return result;
    }

}
