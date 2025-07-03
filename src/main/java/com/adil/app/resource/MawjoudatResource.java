package com.adil.app.resource;

import com.adil.app.dto.MawjoudatDTO;
import com.adil.app.service.MawjoudatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mawjoudats")
@RequiredArgsConstructor
@Slf4j
public class MawjoudatResource {

    private final MawjoudatService mawjoudatService;

    @PostMapping
    public MawjoudatDTO createMawjoudat(@RequestBody MawjoudatDTO mawjoudatDTO) {
        log.info("Start resource : create mawjoudat with details {}", mawjoudatDTO);
        MawjoudatDTO result = mawjoudatService.save(mawjoudatDTO);
        log.info("End resource : create mawjoudat with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public MawjoudatDTO updateMawjoudat(@PathVariable Long id, @RequestBody MawjoudatDTO mawjoudatDTO) {
        log.info("Start resource : update mawjoudat with details {}", mawjoudatDTO);
        MawjoudatDTO result = mawjoudatService.update(id, mawjoudatDTO);
        log.info("End resource : update mawjoudat with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public MawjoudatDTO getMawjoudat(@PathVariable Long id) {
        log.info("Start resource : get mawjoudat with id {}", id);
        var result = mawjoudatService.findById(id);
        log.info("End resource : get mawjoudat with id {}", id);
        return result;
    }
    
    @DeleteMapping("/{id}")
    public void deleteMawjoudat(@PathVariable Long id) {
        log.info("Start resource : delete mawjoudat with id {}", id);
        mawjoudatService.delete(id);
        log.info("End resource : delete mawjoudat with id {}", id);
    }

    @GetMapping
    public Page<MawjoudatDTO> getAllMawjoudats(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all mawjoudats");
        Page<MawjoudatDTO> mawjoudats = mawjoudatService.findAll(page, size);
        log.info("End resource : get all mawjoudats");
        return mawjoudats;
    }
}