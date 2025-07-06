package com.adil.app.resource;

import com.adil.app.dto.CoffreFortDTO;
import com.adil.app.service.CoffreFortService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coffre-fort")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","https://v0-simple-ai-ui-design.vercel.app/"} )
public class CoffreFortController {

    private final CoffreFortService coffreFortService;

    @GetMapping
    public List<CoffreFortDTO> getAllCoffreFort() {
        log.info("Start resource : Retrieving all CoffreFort");
        List<CoffreFortDTO> coffres = coffreFortService.getAllCoffreFort();
        log.info("End resource : Retrieved all CoffreFort, count: {}", coffres.size());
        return coffres;
    }

    @PutMapping("/{id}")
    public CoffreFortDTO updateCoffreFort(@PathVariable Integer id, @RequestBody CoffreFortDTO coffreFortDTO) {
        log.info("Start resource : Updating CoffreFort with ID: {} and details: {}", id, coffreFortDTO);
        CoffreFortDTO updatedCoffreFort = coffreFortService.update(id, coffreFortDTO);
        log.info("End resource : Updated CoffreFort with details: {}", updatedCoffreFort);
        return updatedCoffreFort;
    }

    @GetMapping("/{id}")
    public  CoffreFortDTO getById(@PathVariable Integer id) {
        log.info("Start resource : Retrieving CoffreFort with ID: {}", id);
        CoffreFortDTO coffreFort = coffreFortService.getById(id);
        log.info("End resource : Retrieved CoffreFort with details: {}", coffreFort);
        return coffreFort;
    }

}
