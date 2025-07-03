package com.adil.app.service;

import com.adil.app.domain.MissionMensuelle;
import com.adil.app.dto.MissionMensuelleDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.MissionMensuelleMapper;
import com.adil.app.repository.MissionMensuelleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MissionMensuelleService {

    private final MissionMensuelleRepository missionMensuelleRepository;
    private final MissionMensuelleMapper missionMensuelleMapper;
    
    public MissionMensuelleDTO save(MissionMensuelleDTO missionMensuelleDTO) {
        log.info("Start service : Saving MissionMensuelle with details: {}", missionMensuelleDTO);
        MissionMensuelle missionMensuelle = missionMensuelleMapper.toMissionMensuelle(missionMensuelleDTO);
        missionMensuelle = missionMensuelleRepository.save(missionMensuelle);
        var result = missionMensuelleMapper.toMissionMensuelleDTO(missionMensuelle);
        log.info("End service : Saving MissionMensuelle with details: {}", result);
        return result;
    }
    
    public MissionMensuelleDTO update(Integer id, MissionMensuelleDTO missionMensuelleDTO) {
        log.info("Start service : Updating MissionMensuelle with details: {}", missionMensuelleDTO);
        var existMissionMensuelle = missionMensuelleRepository.findById(id).
                orElseThrow(() -> new FunctionalException("MissionMensuelle not found"));
        missionMensuelleMapper.updatedEntity(existMissionMensuelle, missionMensuelleDTO);
        var savedMissionMensuelle = missionMensuelleRepository.save(existMissionMensuelle);
        var result = missionMensuelleMapper.toMissionMensuelleDTO(savedMissionMensuelle);
        log.info("End service : Updating MissionMensuelle with details: {}", result);
        return result;
    }
    
    public MissionMensuelleDTO findById(Integer id) {
        log.info("Start service : Finding MissionMensuelle with id: {}", id);
        var missionMensuelle = missionMensuelleRepository.findById(id).
                orElseThrow(() -> new FunctionalException("MissionMensuelle not found"));
        var result = missionMensuelleMapper.toMissionMensuelleDTO(missionMensuelle);
        log.info("End service : Finding MissionMensuelle with details: {}", result);
        return result;
    }

    public void delete(Integer id) {
        var missionMensuelle = missionMensuelleRepository.existsById(id);
        if (missionMensuelle) {
            log.info("Start service : Deleting MissionMensuelle with id: {}", id);
            missionMensuelleRepository.deleteById(id);
            log.info("End service : Deleted MissionMensuelle with id: {}", id);
        } else {
            log.warn("MissionMensuelle with id {} not found for deletion", id);
            throw new FunctionalException("MissionMensuelle not found for deletion");
        }
    }

    public Page<MissionMensuelleDTO> findAll(int page, int size) {
        log.info("Start service : Finding all MissionMensuelles");
        Pageable pageable = PageRequest.of(page, size);
        Page<MissionMensuelle> missionMensuelles = missionMensuelleRepository.findAllByOrderByIdAsc(pageable);
        Page<MissionMensuelleDTO> result = missionMensuelles.map(missionMensuelleMapper::toMissionMensuelleDTO);
        log.info("End service : Found {} MissionMensuelles", result.getTotalElements());
        return result;
    }
}