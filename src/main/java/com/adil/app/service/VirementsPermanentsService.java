package com.adil.app.service;

import com.adil.app.domain.VirementsPermanents;
import com.adil.app.dto.VirementsPermanentsDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.VirementsPermanentsMapper;
import com.adil.app.repository.VirementsPermanentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VirementsPermanentsService {

    private final VirementsPermanentsRepository virementsPermanentsRepository;
    private final VirementsPermanentsMapper virementsPermanentsMapper;
    
    public VirementsPermanentsDTO save(VirementsPermanentsDTO virementsPermanentsDTO) {
        log.info("Start service : Saving VirementsPermanents with details: {}", virementsPermanentsDTO);
        VirementsPermanents virementsPermanents = virementsPermanentsMapper.toVirementsPermanents(virementsPermanentsDTO);
        virementsPermanents = virementsPermanentsRepository.save(virementsPermanents);
        var result = virementsPermanentsMapper.toVirementsPermanentsDTO(virementsPermanents);
        log.info("End service : Saving VirementsPermanents with details: {}", result);
        return result;
    }
    
    public VirementsPermanentsDTO update(Integer id, VirementsPermanentsDTO virementsPermanentsDTO) {
        log.info("Start service : Updating VirementsPermanents with details: {}", virementsPermanentsDTO);
        var existVirementsPermanents = virementsPermanentsRepository.findById(id).
                orElseThrow(() -> new FunctionalException("VirementsPermanents not found"));
        virementsPermanentsMapper.updatedEntity(existVirementsPermanents, virementsPermanentsDTO);
        var savedVirementsPermanents = virementsPermanentsRepository.save(existVirementsPermanents);
        var result = virementsPermanentsMapper.toVirementsPermanentsDTO(savedVirementsPermanents);
        log.info("End service : Updating VirementsPermanents with details: {}", result);
        return result;
    }
    
    public VirementsPermanentsDTO findById(Integer id) {
        log.info("Start service : Finding VirementsPermanents with id: {}", id);
        var virementsPermanents = virementsPermanentsRepository.findById(id).
                orElseThrow(() -> new FunctionalException("VirementsPermanents not found"));
        var result = virementsPermanentsMapper.toVirementsPermanentsDTO(virementsPermanents);
        log.info("End service : Finding VirementsPermanents with details: {}", result);
        return result;
    }

    public void delete(Integer id) {
        var virementsPermanents = virementsPermanentsRepository.existsById(id);
        if (virementsPermanents) {
            log.info("Start service : Deleting VirementsPermanents with id: {}", id);
            virementsPermanentsRepository.deleteById(id);
            log.info("End service : Deleted VirementsPermanents with id: {}", id);
        } else {
            log.warn("VirementsPermanents with id {} not found for deletion", id);
            throw new FunctionalException("VirementsPermanents not found for deletion");
        }
    }

    public Page<VirementsPermanentsDTO> findAll(int page, int size) {
        log.info("Start service : Finding all VirementsPermanents");
        Pageable pageable = PageRequest.of(page, size);
        Page<VirementsPermanents> virementsPermanents = virementsPermanentsRepository.findAllByOrderByDateDebutAsc(pageable);
        Page<VirementsPermanentsDTO> result = virementsPermanents.map(virementsPermanentsMapper::toVirementsPermanentsDTO);
        log.info("End service : Found {} VirementsPermanents", result.getTotalElements());
        return result;
    }
}