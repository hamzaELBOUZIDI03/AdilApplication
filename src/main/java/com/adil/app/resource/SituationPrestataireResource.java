package com.adil.app.resource;

import com.adil.app.dto.SituationPrestataireDTO;
import com.adil.app.service.SituationPrestataireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/situation-prestataires")
@RequiredArgsConstructor
@Slf4j
public class SituationPrestataireResource {

    private final SituationPrestataireService situationPrestataireService;

    @PostMapping
    public SituationPrestataireDTO createSituationPrestataire(@RequestBody SituationPrestataireDTO situationPrestataireDTO) {
        log.info("Start resource : create situationPrestataire with details {}", situationPrestataireDTO);
        SituationPrestataireDTO result = situationPrestataireService.save(situationPrestataireDTO);
        log.info("End resource : create situationPrestataire with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public SituationPrestataireDTO updateSituationPrestataire(@PathVariable Integer id, @RequestBody SituationPrestataireDTO situationPrestataireDTO) {
        log.info("Start resource : update situationPrestataire with details {}", situationPrestataireDTO);
        SituationPrestataireDTO result = situationPrestataireService.update(id, situationPrestataireDTO);
        log.info("End resource : update situationPrestataire with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public SituationPrestataireDTO getSituationPrestataire(@PathVariable Integer id) {
        log.info("Start resource : get situationPrestataire with id {}", id);
        var result = situationPrestataireService.findById(id);
        log.info("End resource : get situationPrestataire with id {}", id);
        return result;
    }
    
    @DeleteMapping("/{id}")
    public void deleteSituationPrestataire(@PathVariable Integer id) {
        log.info("Start resource : delete situationPrestataire with id {}", id);
        situationPrestataireService.delete(id);
        log.info("End resource : delete situationPrestataire with id {}", id);
    }

    @GetMapping
    public Page<SituationPrestataireDTO> getAllSituationPrestataires(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all situationPrestataires");
        Page<SituationPrestataireDTO> situationPrestataires = situationPrestataireService.findAll(page, size);
        log.info("End resource : get all situationPrestataires");
        return situationPrestataires;
    }
}