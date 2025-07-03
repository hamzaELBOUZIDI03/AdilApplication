package com.adil.app.resource;

import com.adil.app.dto.IdentifiantDTO;
import com.adil.app.service.IdentifiantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/identifiants")
public class IdentifiantResource {

    private final IdentifiantService identifiantService;

    @PostMapping
    public IdentifiantDTO save(@RequestBody IdentifiantDTO identifiantDTO) {
        log.info("Start resource : Saving Identifiant with details: {}", identifiantDTO);
        IdentifiantDTO result = identifiantService.save(identifiantDTO);
        log.info("End resource : Saving Identifiant with details: {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public IdentifiantDTO update(@PathVariable Integer id, @RequestBody IdentifiantDTO identifiantDTO) {
        log.info("Start resource : Updating Identifiant with details: {}", identifiantDTO);
        IdentifiantDTO result = identifiantService.update(id, identifiantDTO);
        log.info("End resource : Updating Identifiant with details: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public IdentifiantDTO findById(@PathVariable Integer id) {
        log.info("Start resource : Finding Identifiant with id: {}", id);
        IdentifiantDTO result = identifiantService.findById(id);
        log.info("End resource : Finding Identifiant with details: {}", result);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        log.info("Start resource : Deleting Identifiant with id: {}", id);
        identifiantService.delete(id);
        log.info("End resource : Deleted Identifiant with id: {}", id);
    }

    @GetMapping
    public Page<IdentifiantDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : Finding all Identifiants");
        Page<IdentifiantDTO> result = identifiantService.findAll(page, size);
        log.info("End resource : Found all Identifiants");
        return result;
    }

}
