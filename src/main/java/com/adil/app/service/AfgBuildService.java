package com.adil.app.service;

import com.adil.app.domain.AfgBuild;
import com.adil.app.dto.AfgBuildDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.AfgBuildMapper;
import com.adil.app.repository.AfgBuildRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AfgBuildService {

    private final AfgBuildRepository afgBuildRepository;
    private final AfgBuildMapper afgBuildMapper;
    
    public AfgBuildDTO save(AfgBuildDTO afgBuildDTO) {
        log.info("Start service : Saving AfgBuild with details: {}", afgBuildDTO);
        AfgBuild afgBuild = afgBuildMapper.toAfgBuild(afgBuildDTO);
        afgBuild = afgBuildRepository.save(afgBuild);
        var result = afgBuildMapper.toAfgBuildDTO(afgBuild);
        log.info("End service : Saving AfgBuild with details: {}", result);
        return result;
    }
    
    public AfgBuildDTO update(Integer id, AfgBuildDTO afgBuildDTO) {
        log.info("Start service : Updating AfgBuild with details: {}", afgBuildDTO);
        var existAfgBuild = afgBuildRepository.findById(id).
                orElseThrow(() -> new FunctionalException("AfgBuild not found"));
        afgBuildMapper.updatedEntity(existAfgBuild, afgBuildDTO);
        var savedAfgBuild = afgBuildRepository.save(existAfgBuild);
        var result = afgBuildMapper.toAfgBuildDTO(savedAfgBuild);
        log.info("End service : Updating AfgBuild with details: {}", result);
        return result;
    }
    
    public AfgBuildDTO findById(Integer id) {
        log.info("Start service : Finding AfgBuild with id: {}", id);
        var afgBuild =  afgBuildRepository.findById(id).
                orElseThrow(() -> new FunctionalException("AfgBuild not found"));
        var result = afgBuildMapper.toAfgBuildDTO(afgBuild);
        log.info("End service : Finding AfgBuild with details: {}", result);
        return result;
    }

    public void delete(Integer id) {
        var afgBuild = afgBuildRepository.existsById(id);
        if (afgBuild) {
            log.info("Start service : Deleting AfgBuild with id: {}", id);
            afgBuildRepository.deleteById(id);
            log.info("End service : Deleted AfgBuild with id: {}", id);
        } else {
            log.warn("AfgBuild with id {} not found for deletion", id);
            throw new FunctionalException("AfgBuild not found for deletion");
        }
    }

    public Page<AfgBuildDTO> findAll(int page, int size) {
        log.info("Start service : Finding all AfgBuilds");
        Pageable pageable = PageRequest.of(page, size);
        Page<AfgBuild> afgBuilds = afgBuildRepository.findAllByOrderByDateEcrireAsc(pageable);
        var result = afgBuilds.map(afgBuildMapper::toAfgBuildDTO);
        log.info("End service : Found {} AfgBuilds", result.getTotalElements());
        return result;
    }

}