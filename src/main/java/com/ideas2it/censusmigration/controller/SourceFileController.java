package com.ideas2it.censusmigration.controller;

import com.ideas2it.censusmigration.service.SourceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/source-file")
public class SourceFileController {
    private final SourceFileService sourceFileService;

    @Autowired
    public SourceFileController(SourceFileService sourceFileService) {
        this.sourceFileService = sourceFileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> readFile(@RequestParam("filename") MultipartFile file) {

        if (sourceFileService.saveFile(file)) {
            return new ResponseEntity<>(file.getOriginalFilename()+" successfully saved to database", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
