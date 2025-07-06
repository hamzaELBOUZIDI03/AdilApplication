package com.adil.app.resource;

import com.adil.app.dto.CreditDTO;
import com.adil.app.dto.CreditResponse;
import com.adil.app.service.CreditService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/credits")
@CrossOrigin(origins = {"http://localhost:3000","https://v0-simple-ai-ui-design.vercel.app/"} )
public class CreditResource {

    private final CreditService creditService;

    @PostMapping
    public CreditDTO createCredit(@Valid @RequestBody CreditDTO creditDTO) {
        log.info("Start resource : Creating credit with details: {}", creditDTO);
        CreditDTO result = creditService.create(creditDTO);
        log.info("End resource : Created credit with details: {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public CreditDTO updateCredit(@PathVariable Integer id, @Valid @RequestBody CreditDTO creditDTO) {
        log.info("Start resource : Updating credit with ID: {} and details: {}", id, creditDTO);
        CreditDTO result = creditService.update(id, creditDTO);
        log.info("End resource : Updated credit with details: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public CreditDTO getById(@PathVariable Integer id) {
        log.info("Start resource : Retrieving credit with ID: {}", id);
        CreditDTO result = creditService.getById(id);
        log.info("End resource : Retrieved credit with details: {}", result);
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable Integer id) {
        log.info("Start resource : Deleting credit with ID: {}", id);
        creditService.delete(id);
        log.info("End resource : Deleted credit with ID: {}", id);
    }

    @GetMapping
    public CreditResponse getAllCredits(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : Retrieving all credits with pagination: page={}, size={}", page, size);
        CreditResponse credits = creditService.getAll(page, size);
        log.info("End resource : Retrieved all credits");
        return credits;
    }

    @GetMapping("/recherche")
    public List<CreditDTO> searchByNomOrMontant(@RequestParam(required = false, name = "nom") String nom,
                                                @RequestParam(required = false, name = "montant") Double montant) {
        log.info("Start resource : search credits by name {} or montant {}", nom, montant);
        var response = creditService.searchByNomOrMontant(nom, montant);
        log.info("End resource : search credits by name {} or montant {}", nom, montant);
        return response;
    }

}
