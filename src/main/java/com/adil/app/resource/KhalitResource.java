package com.adil.app.resource;

import com.adil.app.dto.KhalitDTO;
import com.adil.app.dto.KhalitResponse;
import com.adil.app.service.KhalitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/khalits")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class KhalitResource {

    private final KhalitService khalitService;

    @PostMapping
    public KhalitDTO create(@Valid @RequestBody KhalitDTO khalitDTO) {
        log.info("Start resource : create khalit with details {}", khalitDTO);
        KhalitDTO result = khalitService.create(khalitDTO);
        log.info("End resource : create khalit with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public KhalitDTO update(@PathVariable Integer id, @Valid @RequestBody KhalitDTO khalitDTO) {
        log.info("Start resource : update khalit with details {}", khalitDTO);
        KhalitDTO result = khalitService.update(id, khalitDTO);
        log.info("End resource : update khalit with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public KhalitDTO getById(@PathVariable Integer id) {
        log.info("Start resource : get khalit with id {}", id);
        KhalitDTO result = khalitService.getById(id);
        log.info("End resource : get khalit with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        log.info("Start resource : delete khalit with id {}", id);
        khalitService.delete(id);
        log.info("End resource : delete khalit with id {}", id);
    }

    @GetMapping
    public KhalitResponse getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all khalits");
        KhalitResponse result = khalitService.getAll(page, size);
        log.info("End resource : get all khalits");
        return result;
    }

    @GetMapping("/recherche")
    public List<KhalitDTO> searchByNameOrMontant(@RequestParam(required = false, name = "nom") String nom,
                                                 @RequestParam(required = false, name = "montant") Double montant) {
        log.info("Start resource : search khalits by name {} or montant {}", nom, montant);
        var response = khalitService.searchByNomOrMontant(nom, montant);
        log.info("End resource : search khalits by name or montant");
        return response;
    }

}
