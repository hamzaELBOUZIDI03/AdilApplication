package com.adil.app.resource;

import com.adil.app.dto.HayRahmaniDTO;
import com.adil.app.service.HayRahmaniService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hay-rahmanis")
@RequiredArgsConstructor
@Slf4j
public class HayRahmaniResource {

    private final HayRahmaniService hayRahmaniService;

    @PostMapping
    public HayRahmaniDTO createHayRahmani(@RequestBody HayRahmaniDTO hayRahmaniDTO) {
        log.info("Start resource : create hay rahmani with details {}", hayRahmaniDTO);
        HayRahmaniDTO result = hayRahmaniService.save(hayRahmaniDTO);
        log.info("End resource : create hay rahmani with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public HayRahmaniDTO updateHayRahmani(@PathVariable Long id, @RequestBody HayRahmaniDTO hayRahmaniDTO) {
        log.info("Start resource : update hay rahmani with details {}", hayRahmaniDTO);
        HayRahmaniDTO result = hayRahmaniService.update(id, hayRahmaniDTO);
        log.info("End resource : update hay rahmani with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public HayRahmaniDTO getHayRahmani(@PathVariable Long id) {
        log.info("Start resource : get hay rahmani with id {}", id);
        var result = hayRahmaniService.findById(id);
        log.info("End resource : get hay rahmani with id {}", id);
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteHayRahmani(@PathVariable Long id) {
        log.info("Start resource : delete hay rahmani with id {}", id);
        hayRahmaniService.delete(id);
        log.info("End resource : delete hay rahmani with id {}", id);
    }

    @GetMapping
    public Page<HayRahmaniDTO> getAllHayRahmanis(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all hay rahmanis");
        Page<HayRahmaniDTO> hayRahmanis = hayRahmaniService.findAll(page, size);
        log.info("End resource : get all hay rahmanis");
        return hayRahmanis;
    }
}