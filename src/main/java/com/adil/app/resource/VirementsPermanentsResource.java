package com.adil.app.resource;

import com.adil.app.dto.VirementsPermanentsDTO;
import com.adil.app.service.VirementsPermanentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/virements-permanents")
@RequiredArgsConstructor
@Slf4j
public class VirementsPermanentsResource {

    private final VirementsPermanentsService virementsPermanentsService;

    @PostMapping
    public VirementsPermanentsDTO createVirementsPermanents(@RequestBody VirementsPermanentsDTO virementsPermanentsDTO) {
        log.info("Start resource : create virementsPermanents with details {}", virementsPermanentsDTO);
        VirementsPermanentsDTO result = virementsPermanentsService.save(virementsPermanentsDTO);
        log.info("End resource : create virementsPermanents with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public VirementsPermanentsDTO updateVirementsPermanents(@PathVariable Integer id, @RequestBody VirementsPermanentsDTO virementsPermanentsDTO) {
        log.info("Start resource : update virementsPermanents with details {}", virementsPermanentsDTO);
        VirementsPermanentsDTO result = virementsPermanentsService.update(id, virementsPermanentsDTO);
        log.info("End resource : update virementsPermanents with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public VirementsPermanentsDTO getVirementsPermanents(@PathVariable Integer id) {
        log.info("Start resource : get virementsPermanents with id {}", id);
        var result = virementsPermanentsService.findById(id);
        log.info("End resource : get virementsPermanents with id {}", id);
        return result;
    }
    
    @DeleteMapping("/{id}")
    public void deleteVirementsPermanents(@PathVariable Integer id) {
        log.info("Start resource : delete virementsPermanents with id {}", id);
        virementsPermanentsService.delete(id);
        log.info("End resource : delete virementsPermanents with id {}", id);
    }

    @GetMapping
    public Page<VirementsPermanentsDTO> getAllVirementsPermanents(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all virementsPermanents");
        Page<VirementsPermanentsDTO> virementsPermanents = virementsPermanentsService.findAll(page, size);
        log.info("End resource : get all virementsPermanents");
        return virementsPermanents;
    }
}