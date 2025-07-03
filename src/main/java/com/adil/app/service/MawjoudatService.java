package com.adil.app.service;

import com.adil.app.domain.Mawjoudat;
import com.adil.app.dto.MawjoudatDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.MawjoudatMapper;
import com.adil.app.repository.MawjoudatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MawjoudatService {

    private final MawjoudatRepository mawjoudatRepository;
    private final MawjoudatMapper mawjoudatMapper;
    
    public MawjoudatDTO save(MawjoudatDTO mawjoudatDTO) {
        log.info("Start service : Saving Mawjoudat with details: {}", mawjoudatDTO);
        Mawjoudat mawjoudat = mawjoudatMapper.toMawjoudat(mawjoudatDTO);
        mawjoudat = mawjoudatRepository.save(mawjoudat);
        var result = mawjoudatMapper.toMawjoudatDTO(mawjoudat);
        log.info("End service : Saving Mawjoudat with details: {}", result);
        return result;
    }
    
    public MawjoudatDTO update(Long id, MawjoudatDTO mawjoudatDTO) {
        log.info("Start service : Updating Mawjoudat with details: {}", mawjoudatDTO);
        var existMawjoudat = mawjoudatRepository.findById(id).
                orElseThrow(() -> new FunctionalException("Mawjoudat not found"));
        mawjoudatMapper.updatedEntity(existMawjoudat, mawjoudatDTO);
        var savedMawjoudat = mawjoudatRepository.save(existMawjoudat);
        var result = mawjoudatMapper.toMawjoudatDTO(savedMawjoudat);
        log.info("End service : Updating Mawjoudat with details: {}", result);
        return result;
    }
    
    public MawjoudatDTO findById(Long id) {
        log.info("Start service : Finding Mawjoudat with id: {}", id);
        var mawjoudat = mawjoudatRepository.findById(id).
                orElseThrow(() -> new FunctionalException("Mawjoudat not found"));
        var result = mawjoudatMapper.toMawjoudatDTO(mawjoudat);
        log.info("End service : Finding Mawjoudat with details: {}", result);
        return result;
    }

    public void delete(Long id) {
        var mawjoudat = mawjoudatRepository.existsById(id);
        if (mawjoudat) {
            log.info("Start service : Deleting Mawjoudat with id: {}", id);
            mawjoudatRepository.deleteById(id);
            log.info("End service : Deleted Mawjoudat with id: {}", id);
        } else {
            log.warn("Mawjoudat with id {} not found for deletion", id);
            throw new FunctionalException("Mawjoudat not found for deletion");
        }
    }

    public Page<MawjoudatDTO> findAll(int page, int size) {
        log.info("Start service : Finding all Mawjoudats");
        Pageable pageable = PageRequest.of(page, size);
        Page<Mawjoudat> mawjoudats = mawjoudatRepository.findAllByOrderByDateEcrireAsc(pageable);
        Page<MawjoudatDTO> result = mawjoudats.map(mawjoudatMapper::toMawjoudatDTO);
        log.info("End service : Found {} Mawjoudats", result.getTotalElements());
        return result;
    }
}