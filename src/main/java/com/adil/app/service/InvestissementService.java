package com.adil.app.service;

import com.adil.app.domain.Investissement;
import com.adil.app.dto.InvestissementDTO;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.InvestissementMapper;
import com.adil.app.repository.InvestissementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvestissementService {

    private final InvestissementRepository investissementRepository;
    private final InvestissementMapper investissementMapper;

    public InvestissementDTO save(InvestissementDTO investissementDTO) {
        log.info("Start service : Saving Investissement with details: {}", investissementDTO);
        Investissement investissement = investissementMapper.toInvestissement(investissementDTO);
        investissement = investissementRepository.save(investissement);
        var result = investissementMapper.toInvestissementDTO(investissement);
        log.info("End service : Saving Investissement with details: {}", result);
        return result;
    }

    public InvestissementDTO update(Long id, InvestissementDTO investissementDTO) {
        log.info("Start service : Updating Investissement with details: {}", investissementDTO);
        var existInvestissement = investissementRepository.findById(id).
                orElseThrow(() -> new FunctionalException("Investissement not found"));
        investissementMapper.updatedEntity(existInvestissement, investissementDTO);
        var savedInvestissement = investissementRepository.save(existInvestissement);
        var result = investissementMapper.toInvestissementDTO(savedInvestissement);
        log.info("End service : Updating Investissement with details: {}", result);
        return result;
    }

    public InvestissementDTO findById(Long id) {
        log.info("Start service : Finding Investissement with id: {}", id);
        var investissement = investissementRepository.findById(id).
                orElseThrow(() -> new FunctionalException("Investissement not found"));
        var result = investissementMapper.toInvestissementDTO(investissement);
        log.info("End service : Finding Investissement with details: {}", result);
        return result;
    }

    public void delete(Long id) {
        var investissement = investissementRepository.existsById(id);
        if (investissement) {
            log.info("Start service : Deleting Investissement with id: {}", id);
            investissementRepository.deleteById(id);
            log.info("End service : Deleted Investissement with id: {}", id);
        } else {
            log.warn("Investissement with id {} not found for deletion", id);
            throw new FunctionalException("Investissement not found for deletion");
        }
    }

    public Page<InvestissementDTO> findAll(int page, int size) {
        log.info("Start service : Finding all Investissements");
        Pageable pageable = PageRequest.of(page, size);
        Page<Investissement> investissements = investissementRepository.findAllByOrderByDateEcrireAsc(pageable);
        var result = investissements.map(investissementMapper::toInvestissementDTO);
        log.info("End service : Found {} Investissements", result.getTotalElements());
        return result;
    }
}