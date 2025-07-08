package com.adil.app.resource;

import com.adil.app.dto.Adil3DTO;
import com.adil.app.dto.Adil3Response;
import com.adil.app.service.Adil3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adils-3")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://v0-simple-ai-ui-design.vercel.app/"})
public class Adil3Resource {

    private final Adil3Service adilService;

    @PostMapping
    public Adil3DTO create(@RequestBody Adil3DTO adilDTO) {
        log.info("Start resource : save adil3 with details {}", adilDTO);
        Adil3DTO result = adilService.create(adilDTO);
        log.info("End resource : save adil3 with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public Adil3DTO update(@PathVariable Integer id, @RequestBody Adil3DTO adilDTO) {
        log.info("Start resource : update adil3 with details {}", adilDTO);
        Adil3DTO result = adilService.update(id, adilDTO);
        log.info("End resource : update adil3 with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public Adil3DTO getById(@PathVariable Integer id) {
        log.info("Start resource : get adil3 with id {}", id);
        Adil3DTO result = adilService.getById(id);
        log.info("End resource : get adil3 with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        log.info("Start resource : delete adil3 with id {}", id);
        adilService.delete(id);
        log.info("End resource : delete adil3 with id {}", id);
    }

    @GetMapping
    public Adil3Response getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all adils3");
        Adil3Response response = adilService.getAll(page, size);
        log.info("End resource : get all adils3");
        return response;
    }

    @GetMapping("/recherche")
    public List<Adil3DTO> searchByNameOrMontant(@RequestParam(required = false, name = "nom") String nom,
                                                @RequestParam(required = false, name = "montant") Double montant) {
        log.info("Start resource : search adils3 by name {} or montant {}", nom, montant);
        var response = adilService.searchByNomOrMontant(nom, montant);
        log.info("End resource : search adils3 by name or montant");
        return response;
    }


}
