package com.ideas2it.censusmigration.service;

import org.springframework.web.multipart.MultipartFile;

public interface SourceFileService {
    public boolean saveFile(MultipartFile file);
}
