package com.adil.app.service;

import com.adil.app.domain.HayRahmani;
import com.adil.app.dto.HayRahmaniDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.HayRahmaniMapper;
import com.adil.app.repository.HayRahmaniRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HayRahmaniService {

    private final HayRahmaniRepository hayRahmaniRepository;
    private final HayRahmaniMapper hayRahmaniMapper;
    
    public HayRahmaniDTO save(HayRahmaniDTO hayRahmaniDTO) {
        log.info("Start service : Saving HayRahmani with details: {}", hayRahmaniDTO);
        HayRahmani hayRahmani = hayRahmaniMapper.toHayRahmani(hayRahmaniDTO);
        hayRahmani = hayRahmaniRepository.save(hayRahmani);
        var result = hayRahmaniMapper.toHayRahmaniDTO(hayRahmani);
        log.info("End service : Saving HayRahmani with details: {}", result);
        return result;
    }
    
    public HayRahmaniDTO update(Long id, HayRahmaniDTO hayRahmaniDTO) {
        log.info("Start service : Updating HayRahmani with details: {}", hayRahmaniDTO);
        var existHayRahmani = hayRahmaniRepository.findById(id).
                orElseThrow(() -> new FunctionalException("HayRahmani not found"));
        hayRahmaniMapper.updatedEntity(existHayRahmani, hayRahmaniDTO);
        var savedHayRahmani = hayRahmaniRepository.save(existHayRahmani);
        var result = hayRahmaniMapper.toHayRahmaniDTO(savedHayRahmani);
        log.info("End service : Updating HayRahmani with details: {}", result);
        return result;
    }
    
    public HayRahmaniDTO findById(Long id) {
        log.info("Start service : Finding HayRahmani with id: {}", id);
        var hayRahmani = hayRahmaniRepository.findById(id).
                orElseThrow(() -> new FunctionalException("HayRahmani not found"));
        var result = hayRahmaniMapper.toHayRahmaniDTO(hayRahmani);
        log.info("End service : Finding HayRahmani with details: {}", result);
        return result;
    }

    public void delete(Long id) {
        var hayRahmani = hayRahmaniRepository.existsById(id);
        if (hayRahmani) {
            log.info("Start service : Deleting HayRahmani with id: {}", id);
            hayRahmaniRepository.deleteById(id);
            log.info("End service : Deleted HayRahmani with id: {}", id);
        } else {
            log.warn("HayRahmani with id {} not found for deletion", id);
            throw new FunctionalException("HayRahmani not found for deletion");
        }
    }

    public Page<HayRahmaniDTO> findAll(int page, int size) {
        log.info("Start service : Finding all HayRahmanis");
        Pageable pageable = PageRequest.of(page, size);
        Page<HayRahmani> hayRahmanis = hayRahmaniRepository.findAllByOrderByDateEcrireAsc(pageable);
        var result = hayRahmanis.map(hayRahmaniMapper::toHayRahmaniDTO);
        log.info("End service : Found {} HayRahmanis", result.getTotalElements());
        return result;
    }
}