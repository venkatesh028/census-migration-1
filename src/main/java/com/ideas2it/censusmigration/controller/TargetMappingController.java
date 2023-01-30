package com.ideas2it.censusmigration.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ideas2it.censusmigration.model.TargetMapping;
import com.ideas2it.censusmigration.service.TargetMappingService;
import com.ideas2it.censusmigration.util.Logger.CustomLogger;

@RestController
@RequestMapping("/api/v1/target-mappings")
public class TargetMappingController {
    private final TargetMappingService targetMappingService;
    private final CustomLogger logger = new CustomLogger(TargetMappingController.class);
    public TargetMappingController(TargetMappingService targetMappingService) {
        this.targetMappingService = targetMappingService;
    }

    @PostMapping("/{sourceEhrName}/{serviceLine}")
    public List<TargetMapping> createTargetMapping(@RequestBody MultipartFile file, @PathVariable String sourceEhrName,
                                                   @PathVariable String serviceLine){
        logger.info("Inside the Controller");
        return targetMappingService.createTargetMapping(file,sourceEhrName,serviceLine);
    }

}
