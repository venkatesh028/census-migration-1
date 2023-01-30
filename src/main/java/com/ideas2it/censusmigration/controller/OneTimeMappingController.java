package com.ideas2it.censusmigration.controller;

import java.util.List;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.censusmigration.model.EhrMapping;
import com.ideas2it.censusmigration.service.OneTimeMappingService;

@RestController
@RequestMapping("/api/v1/mappings")
public class OneTimeMappingController {
    private final OneTimeMappingService oneTimeMappingService;

    public OneTimeMappingController(OneTimeMappingService oneTimeMappingService) {
        this.oneTimeMappingService = oneTimeMappingService;
    }

    @PostMapping
    public EhrMapping createEhrMapping(@RequestBody EhrMapping ehrMapping){
        return oneTimeMappingService.createEhrMapping(ehrMapping);
    }

    @PutMapping
    public EhrMapping updateEhrMapping(@RequestBody EhrMapping ehrMapping){
        return oneTimeMappingService.updateEhrMapping(ehrMapping);
    }

    @GetMapping("/{sourceEhrName}")
    public List<EhrMapping> getEhrMapping(@PathVariable String sourceEhrName){
        return oneTimeMappingService.getEhrMappingsBasedOnSourceEhrName(sourceEhrName);
    }
}
