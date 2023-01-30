package com.ideas2it.censusmigration.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ideas2it.censusmigration.model.TargetMapping;

public interface TargetMappingService {
    List<TargetMapping> createTargetMapping(MultipartFile file, String sourceEhrName,
                                            String serviceLine);

}
