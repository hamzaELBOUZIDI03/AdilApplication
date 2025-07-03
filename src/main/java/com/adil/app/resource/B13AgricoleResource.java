package com.adil.app.resource;

import com.adil.app.dto.B13AgricoleDTO;
import com.adil.app.service.B13AgricoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/b13-agricoles")
@RequiredArgsConstructor
@Slf4j
public class B13AgricoleResource {

    private final B13AgricoleService b13AgricoleService;

    @PostMapping
    public B13AgricoleDTO createB13Agricole(@RequestBody B13AgricoleDTO b13AgricoleDTO) {
        log.info("Start resource : create b13 agricole with details {}", b13AgricoleDTO);
        B13AgricoleDTO result = b13AgricoleService.save(b13AgricoleDTO);
        log.info("End resource : create b13 agricole with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public B13AgricoleDTO updateB13Agricole(@PathVariable Long id, @RequestBody B13AgricoleDTO b13AgricoleDTO) {
        log.info("Start resource : update b13 agricole with details {}", b13AgricoleDTO);
        B13AgricoleDTO result = b13AgricoleService.update(id, b13AgricoleDTO);
        log.info("End resource : update b13 agricole with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public B13AgricoleDTO getB13Agricole(@PathVariable Long id) {
        log.info("Start resource : get b13 agricole with id {}", id);
        var result = b13AgricoleService.findById(id);
        log.info("End resource : get b13 agricole with id {}", id);
        return result;
    }
    
    @DeleteMapping("/{id}")
    public void deleteB13Agricole(@PathVariable Long id) {
        log.info("Start resource : delete b13 agricole with id {}", id);
        b13AgricoleService.delete(id);
        log.info("End resource : delete b13 agricole with id {}", id);
    }

    @GetMapping
    public Page<B13AgricoleDTO> getAllB13Agricoles(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all b13 agricoles");
        Page<B13AgricoleDTO> b13Agricoles = b13AgricoleService.findAll(page, size);
        log.info("End resource : get all b13 agricoles");
        return b13Agricoles;
    }
}