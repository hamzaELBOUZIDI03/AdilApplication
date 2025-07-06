package com.adil.app.resource;

import com.adil.app.dto.AdilDTO;
import com.adil.app.dto.AdilResponse;
import com.adil.app.service.AdilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adils")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AdilResource {

    private final AdilService adilService;

    @PostMapping
    public AdilDTO create(@RequestBody AdilDTO adilDTO) {
        log.info("Start resource : save adil with details {}", adilDTO);
        AdilDTO result = adilService.create(adilDTO);
        log.info("End resource : save adil with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public AdilDTO update(@PathVariable Integer id, @RequestBody AdilDTO adilDTO) {
        log.info("Start resource : update adil with details {}", adilDTO);
        AdilDTO result = adilService.update(id, adilDTO);
        log.info("End resource : update adil with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public AdilDTO getById(@PathVariable Integer id) {
        log.info("Start resource : get adil with id {}", id);
        AdilDTO result = adilService.getById(id);
        log.info("End resource : get adil with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        log.info("Start resource : delete adil with id {}", id);
        adilService.delete(id);
        log.info("End resource : delete adil with id {}", id);
    }

    @GetMapping
    public AdilResponse getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all adils");
        AdilResponse response = adilService.getAll(page, size);
        log.info("End resource : get all adils");
        return response;
    }

    @GetMapping("/recherche")
    public List<AdilDTO> searchByNameOrMontant(@RequestParam(required = false, name = "nom") String nom,
                                               @RequestParam(required = false, name = "montant") Double montant) {
        log.info("Start resource : search adils by name {} or montant {}", nom, montant);
        var response = adilService.searchByNomOrMontant(nom, montant);
        log.info("End resource : search adils by name or montant");
        return response;
    }


}
