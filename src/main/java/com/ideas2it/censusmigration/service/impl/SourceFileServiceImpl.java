package com.ideas2it.censusmigration.service.impl;

import com.ideas2it.censusmigration.model.SourceExcelRecord;
import com.ideas2it.censusmigration.repository.SourceEhrRepository;
import com.ideas2it.censusmigration.service.SourceFileService;
import com.ideas2it.censusmigration.util.customException.InvalidFileFormatException;
import com.ideas2it.censusmigration.util.helper.FileReadHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SourceFileServiceImpl implements SourceFileService {
    private final SourceEhrRepository sourceEhrRepository;
    private static final Logger logger = LogManager.getLogger(SourceFileServiceImpl.class);

    public SourceFileServiceImpl(SourceEhrRepository sourceEhrRepository) {
        this.sourceEhrRepository = sourceEhrRepository;
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        boolean isValidFormat = FileReadHelper.checkFileFormat(file);
        List<SourceExcelRecord> patientRecords;
        if (isValidFormat) {
            patientRecords = FileReadHelper.excelRowsToDbRecordsConverter(file);
            if (patientRecords.isEmpty()) {
                return false;
            } else {
                sourceEhrRepository.saveAll(patientRecords);
                return true;
            }
        } else {
            logger.error("Invalid file format provided filename: " + file.getOriginalFilename());
            throw new InvalidFileFormatException("Invalid file format(" + file.getContentType() + "). Please check the format of the file: " + file.getOriginalFilename());
        }
    }
}
