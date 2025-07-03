package com.adil.app.resource;

import com.adil.app.dto.MissionMensuelleDTO;
import com.adil.app.service.MissionMensuelleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mission-mensuelles")
@RequiredArgsConstructor
@Slf4j
public class MissionMensuelleResource {

    private final MissionMensuelleService missionMensuelleService;

    @PostMapping
    public MissionMensuelleDTO createMissionMensuelle(@RequestBody MissionMensuelleDTO missionMensuelleDTO) {
        log.info("Start resource : create missionMensuelle with details {}", missionMensuelleDTO);
        MissionMensuelleDTO result = missionMensuelleService.save(missionMensuelleDTO);
        log.info("End resource : create missionMensuelle with details {}", result);
        return result;
    }

    @PutMapping("/{id}")
    public MissionMensuelleDTO updateMissionMensuelle(@PathVariable Integer id, @RequestBody MissionMensuelleDTO missionMensuelleDTO) {
        log.info("Start resource : update missionMensuelle with details {}", missionMensuelleDTO);
        MissionMensuelleDTO result = missionMensuelleService.update(id, missionMensuelleDTO);
        log.info("End resource : update missionMensuelle with details {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public MissionMensuelleDTO getMissionMensuelle(@PathVariable Integer id) {
        log.info("Start resource : get missionMensuelle with id {}", id);
        var result = missionMensuelleService.findById(id);
        log.info("End resource : get missionMensuelle with id {}", id);
        return result;
    }
    
    @DeleteMapping("/{id}")
    public void deleteMissionMensuelle(@PathVariable Integer id) {
        log.info("Start resource : delete missionMensuelle with id {}", id);
        missionMensuelleService.delete(id);
        log.info("End resource : delete missionMensuelle with id {}", id);
    }

    @GetMapping
    public Page<MissionMensuelleDTO> getAllMissionMensuelles(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "30") int size) {
        log.info("Start resource : get all missionMensuelles");
        Page<MissionMensuelleDTO> missionMensuelles = missionMensuelleService.findAll(page, size);
        log.info("End resource : get all missionMensuelles");
        return missionMensuelles;
    }
}