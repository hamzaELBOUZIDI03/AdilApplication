package com.adil.app.resource;

import com.adil.app.dto.InvestissementDTO;
import com.adil.app.service.InvestissementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/investissements")
@RequiredArgsConstructor
@Slf4j
public class InvestissementResource {

    private final InvestissementService investissementService;

    @PostMapping
    public InvestissementDTO createInvestissement(@RequestBody InvestissementDTO investissementDTO) {
        log.info("Start resource : create investissement with details {}", investissementDTO);
        InvestissementDTO result = investissementService.save(investissementDTO);
        log.info("End resource : create investissement with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public InvestissementDTO updateInvestissement(@PathVariable Long id, @RequestBody InvestissementDTO investissementDTO) {
        log.info("Start resource : update investissement with details {}", investissementDTO);
        InvestissementDTO result = investissementService.update(id, investissementDTO);
        log.info("End resource : update investissement with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public InvestissementDTO getInvestissement(@PathVariable Long id) {
        log.info("Start resource : get investissement with id {}", id);
        var result = investissementService.findById(id);
        log.info("End resource : get investissement with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteInvestissement(@PathVariable Long id) {
        log.info("Start resource : delete investissement with id {}", id);
        investissementService.delete(id);
        log.info("End resource : delete investissement with id {}", id);
    }

    @GetMapping
    public Page<InvestissementDTO> getAllInvestissements(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all investissements");
        Page<InvestissementDTO> investissements = investissementService.findAll(page, size);
        log.info("End resource : get all investissements");
        return investissements;
    }
}