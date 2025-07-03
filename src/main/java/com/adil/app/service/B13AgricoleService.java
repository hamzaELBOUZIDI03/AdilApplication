package com.adil.app.service;

import com.adil.app.domain.B13Agricole;
import com.adil.app.dto.B13AgricoleDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.B13AgricoleMapper;
import com.adil.app.repository.B13AgricoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class B13AgricoleService {

    private final B13AgricoleRepository b13AgricoleRepository;
    private final B13AgricoleMapper b13AgricoleMapper;
    
    public B13AgricoleDTO save(B13AgricoleDTO b13AgricoleDTO) {
        log.info("Start service : Saving B13Agricole with details: {}", b13AgricoleDTO);
        B13Agricole b13Agricole = b13AgricoleMapper.toB13Agricole(b13AgricoleDTO);
        b13Agricole = b13AgricoleRepository.save(b13Agricole);
        var result = b13AgricoleMapper.toB13AgricoleDTO(b13Agricole);
        log.info("End service : Saving B13Agricole with details: {}", result);
        return result;
    }
    
    public B13AgricoleDTO update(Long id, B13AgricoleDTO b13AgricoleDTO) {
        log.info("Start service : Updating B13Agricole with details: {}", b13AgricoleDTO);
        var existB13Agricole = b13AgricoleRepository.findById(id).
                orElseThrow(() -> new FunctionalException("B13Agricole not found"));
        b13AgricoleMapper.updatedEntity(existB13Agricole, b13AgricoleDTO);
        var savedB13Agricole = b13AgricoleRepository.save(existB13Agricole);
        var result = b13AgricoleMapper.toB13AgricoleDTO(savedB13Agricole);
        log.info("End service : Updating B13Agricole with details: {}", result);
        return result;
    }
    
    public B13AgricoleDTO findById(Long id) {
        log.info("Start service : Finding B13Agricole with id: {}", id);
        var b13Agricole = b13AgricoleRepository.findById(id).
                orElseThrow(() -> new FunctionalException("B13Agricole not found"));
        var result = b13AgricoleMapper.toB13AgricoleDTO(b13Agricole);
        log.info("End service : Finding B13Agricole with details: {}", result);
        return result;
    }

    public void delete(Long id) {
        var b13Agricole = b13AgricoleRepository.existsById(id);
        if (b13Agricole) {
            log.info("Start service : Deleting B13Agricole with id: {}", id);
            b13AgricoleRepository.deleteById(id);
            log.info("End service : Deleted B13Agricole with id: {}", id);
        } else {
            log.warn("B13Agricole with id {} not found for deletion", id);
            throw new FunctionalException("B13Agricole not found for deletion");
        }
    }

    public Page<B13AgricoleDTO> findAll(int page, int size) {
        log.info("Start service : Finding all B13Agricoles");
        Pageable pageable = PageRequest.of(page, size);
        Page<B13Agricole> b13Agricoles = b13AgricoleRepository.findAllByOrderByDateEcrireAsc(pageable);
        var result = b13Agricoles.map(b13AgricoleMapper::toB13AgricoleDTO);
        log.info("End service : Found {} B13Agricoles", result.getTotalElements());
        return result;
    }
}