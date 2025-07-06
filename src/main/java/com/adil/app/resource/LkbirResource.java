package com.adil.app.resource;

import com.adil.app.dto.LkbirDTO;
import com.adil.app.dto.LkbirResponse;
import com.adil.app.service.LkbirService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lkbirs")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000","https://v0-simple-ai-ui-design.vercel.app/"} )
public class LkbirResource {

    private final LkbirService lkbirService;

    @PostMapping
    public LkbirDTO create(@Valid @RequestBody LkbirDTO lkbirDTO) {
        log.info("Start resource : create lkbir with details {}", lkbirDTO);
        LkbirDTO result = lkbirService.create(lkbirDTO);
        log.info("End resource : create lkbir with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public LkbirDTO update(@PathVariable Integer id, @Valid @RequestBody LkbirDTO lkbirDTO) {
        log.info("Start resource : update lkbir with details {}", lkbirDTO);
        LkbirDTO result = lkbirService.update(id, lkbirDTO);
        log.info("End resource : update lkbir with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public LkbirDTO getById(@PathVariable Integer id) {
        log.info("Start resource : get lkbir with id {}", id);
        LkbirDTO result = lkbirService.getById(id);
        log.info("End resource : get lkbir with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        log.info("Start resource : delete lkbir with id {}", id);
        lkbirService.delete(id);
        log.info("End resource : delete lkbir with id {}", id);
    }

    @GetMapping
    public LkbirResponse getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all lkbirs");
        LkbirResponse result = lkbirService.getAll(page, size);
        log.info("End resource : get all lkbirs");
        return result;
    }

    @GetMapping("/recherche")
    public List<LkbirDTO> searchByNameOrMontant(@RequestParam(required = false, name = "nom") String nom,
                                                @RequestParam(required = false, name = "montant") Double montant) {
        log.info("Start resource : search lkbirs by name {} or montant {}", nom, montant);
        var response = lkbirService.searchByNomOrMontant(nom, montant);
        log.info("End resource : search lkbirs by name or montant");
        return response;
    }

}
