package com.adil.app.resource;

import com.adil.app.dto.IltizamatDTO;
import com.adil.app.service.IltizamatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/iltizamats")
@RequiredArgsConstructor
@Slf4j
public class IltizamatResource {

    private final IltizamatService iltizamatService;

    @PostMapping
    public IltizamatDTO createIltizamat(@RequestBody IltizamatDTO iltizamatDTO) {
        log.info("Start resource : create iltizamat with details {}", iltizamatDTO);
        IltizamatDTO result = iltizamatService.save(iltizamatDTO);
        log.info("End resource : create iltizamat with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public IltizamatDTO updateIltizamat(@PathVariable Long id, @RequestBody IltizamatDTO iltizamatDTO) {
        log.info("Start resource : update iltizamat with details {}", iltizamatDTO);
        IltizamatDTO result = iltizamatService.update(id, iltizamatDTO);
        log.info("End resource : update iltizamat with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public IltizamatDTO getIltizamat(@PathVariable Long id) {
        log.info("Start resource : get iltizamat with id {}", id);
        var result = iltizamatService.findById(id);
        log.info("End resource : get iltizamat with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteIltizamat(@PathVariable Long id) {
        log.info("Start resource : delete iltizamat with id {}", id);
        iltizamatService.delete(id);
        log.info("End resource : delete iltizamat with id {}", id);
    }

    @GetMapping
    public Page<IltizamatDTO> getAllIltizamats(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all iltizamats");
        Page<IltizamatDTO> iltizamats = iltizamatService.findAll(page, size);
        log.info("End resource : get all iltizamats");
        return iltizamats;
    }
}