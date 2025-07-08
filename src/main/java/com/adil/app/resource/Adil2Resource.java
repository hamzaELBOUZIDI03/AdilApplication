package com.adil.app.resource;

import com.adil.app.dto.Adil2DTO;
import com.adil.app.dto.Adil2Response;
import com.adil.app.service.Adil2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adils-2")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://v0-simple-ai-ui-design.vercel.app/"})
public class Adil2Resource {

    private final Adil2Service adilService;

    @PostMapping
    public Adil2DTO create(@RequestBody Adil2DTO adilDTO) {
        log.info("Start resource : save adil2 with details {}", adilDTO);
        Adil2DTO result = adilService.create(adilDTO);
        log.info("End resource : save adil2 with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public Adil2DTO update(@PathVariable Integer id, @RequestBody Adil2DTO adilDTO) {
        log.info("Start resource : update adil2 with details {}", adilDTO);
        Adil2DTO result = adilService.update(id, adilDTO);
        log.info("End resource : update adil2 with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public Adil2DTO getById(@PathVariable Integer id) {
        log.info("Start resource : get adil2 with id {}", id);
        Adil2DTO result = adilService.getById(id);
        log.info("End resource : get adil2 with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        log.info("Start resource : delete adil2 with id {}", id);
        adilService.delete(id);
        log.info("End resource : delete adil2 with id {}", id);
    }

    @GetMapping
    public Adil2Response getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all adils2");
        Adil2Response response = adilService.getAll(page, size);
        log.info("End resource : get all adils2");
        return response;
    }

    @GetMapping("/recherche")
    public List<Adil2DTO> searchByNameOrMontant(@RequestParam(required = false, name = "nom") String nom,
                                                @RequestParam(required = false, name = "montant") Double montant) {
        log.info("Start resource : search adils2 by name {} or montant {}", nom, montant);
        var response = adilService.searchByNomOrMontant(nom, montant);
        log.info("End resource : search adils2 by name or montant");
        return response;
    }


}
