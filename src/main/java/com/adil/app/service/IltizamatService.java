package com.adil.app.service;

import com.adil.app.domain.Iltizamat;
import com.adil.app.dto.IltizamatDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.IltizamatMapper;
import com.adil.app.repository.IltizamatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IltizamatService {

    private final IltizamatRepository iltizamatRepository;
    private final IltizamatMapper iltizamatMapper;

    public IltizamatDTO save(IltizamatDTO iltizamatDTO) {
        log.info("Start service : Saving Iltizamat with details: {}", iltizamatDTO);
        Iltizamat iltizamat = iltizamatMapper.toIltizamat(iltizamatDTO);
        iltizamat = iltizamatRepository.save(iltizamat);
        var result = iltizamatMapper.toIltizamatDTO(iltizamat);
        log.info("End service : Saving Iltizamat with details: {}", result);
        return result;
    }

    public IltizamatDTO update(Long id, IltizamatDTO iltizamatDTO) {
        log.info("Start service : Updating Iltizamat with details: {}", iltizamatDTO);
        var existIltizamat = iltizamatRepository.findById(id).
                orElseThrow(() -> new FunctionalException("Iltizamat not found"));
        iltizamatMapper.updatedEntity(existIltizamat, iltizamatDTO);
        var savedIltizamat = iltizamatRepository.save(existIltizamat);
        var result = iltizamatMapper.toIltizamatDTO(savedIltizamat);
        log.info("End service : Updating Iltizamat with details: {}", result);
        return result;
    }

    public IltizamatDTO findById(Long id) {
        log.info("Start service : Finding Iltizamat with id: {}", id);
        var iltizamat = iltizamatRepository.findById(id).
                orElseThrow(() -> new FunctionalException("Iltizamat not found"));
        var result = iltizamatMapper.toIltizamatDTO(iltizamat);
        log.info("End service : Finding Iltizamat with details: {}", result);
        return result;
    }

    public void delete(Long id) {
        var iltizamat = iltizamatRepository.existsById(id);
        if (iltizamat) {
            log.info("Start service : Deleting Iltizamat with id: {}", id);
            iltizamatRepository.deleteById(id);
            log.info("End service : Deleted Iltizamat with id: {}", id);
        } else {
            log.warn("Iltizamat with id {} not found for deletion", id);
            throw new FunctionalException("Iltizamat not found for deletion");
        }
    }

    public Page<IltizamatDTO> findAll(int page, int size) {
        log.info("Start service : Finding all Iltizamats");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Iltizamat> iltizamats = iltizamatRepository.findAllByOrderByDateEcrireAsc(pageable);
        var result = iltizamats.map(iltizamatMapper::toIltizamatDTO);
        log.info("End service : Found {} Iltizamats", result.getTotalElements());
        return result;
    }
}