package com.adil.app.resource;

import com.adil.app.dto.AfgBuildDTO;
import com.adil.app.service.AfgBuildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/afg-builds")
@RequiredArgsConstructor
@Slf4j
public class AfgBuildResource {

    private final AfgBuildService afgBuildService;

    @PostMapping
    public AfgBuildDTO createAfgBuild(@RequestBody AfgBuildDTO afgBuildDTO) {
        log.info("Start resource : create afg build with details {}", afgBuildDTO);
        AfgBuildDTO result = afgBuildService.save(afgBuildDTO);
        log.info("End resource : create afg build with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public AfgBuildDTO updateAfgBuild(@PathVariable Integer id, @RequestBody AfgBuildDTO afgBuildDTO) {
        log.info("Start resource : update afg build with details {}", afgBuildDTO);
        AfgBuildDTO result = afgBuildService.update(id, afgBuildDTO);
        log.info("End resource : update afg build with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public AfgBuildDTO getAfgBuild(@PathVariable Integer id) {
        log.info("Start resource : get afg build with id {}", id);
        var result = afgBuildService.findById(id);
        log.info("End resource : get afg build with id {}", id);
        return result;
    }
    
    @DeleteMapping("/{id}")
    public void deleteAfgBuild(@PathVariable Integer id) {
        log.info("Start resource : delete afg build with id {}", id);
        afgBuildService.delete(id);
        log.info("End resource : delete afg build with id {}", id);
    }

    @GetMapping
    public Page<AfgBuildDTO> getAllAfgBuilds(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all afg builds");
        Page<AfgBuildDTO> afgBuilds = afgBuildService.findAll(page, size);
        log.info("End resource : get all afg builds");
        return afgBuilds;
    }

}