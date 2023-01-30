package com.ideas2it.censusmigration.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ideas2it.censusmigration.model.EhrMapping;
import com.ideas2it.censusmigration.model.TargetMapping;
import com.ideas2it.censusmigration.repository.TargetMappingRepository;
import com.ideas2it.censusmigration.service.OneTimeMappingService;
import com.ideas2it.censusmigration.service.TargetMappingService;

@Service
public class TargetMappingServiceImpl implements TargetMappingService {
    private final TargetMappingRepository targetMappingRepository;
    private final OneTimeMappingService oneTimeMappingService;
    public TargetMappingServiceImpl(TargetMappingRepository targetMappingRepository,
                                    OneTimeMappingService oneTimeMappingService) {
        this.targetMappingRepository = targetMappingRepository;
        this.oneTimeMappingService = oneTimeMappingService;
    }

    @Override
    public List<TargetMapping> createTargetMapping(MultipartFile file, String sourceEhrName,
                                                   String serviceLine){
        List<EhrMapping> ehrMappings = oneTimeMappingService.getEhrMappingsBasedOnSourceEhrAndServiceLine(sourceEhrName,
                serviceLine);
        List<String> sourceFields = new ArrayList<>();
        List<String> targetFields = new ArrayList<>();
        List<TargetMapping> targetMappings = new ArrayList<>();
        Map<String, Object> attributes;

        ehrMappings.forEach(ehrMapping -> {
            sourceFields.add(ehrMapping.getSourceFieldName());
            targetFields.add(ehrMapping.getTargetFieldName());
        });

        for(List<String>  cells : getSourceFieldValues(file, sourceFields)){
            TargetMapping targetMapping = new TargetMapping();
            attributes = new HashMap<>();
            targetMapping.setSourceEhrName(sourceEhrName);
            targetMapping.setServiceLine(serviceLine);

            for (int i = 0; i < cells.size(); i++){
                attributes.put(targetFields.get(i), cells.get(i));
            }
            targetMapping.setPatientAttributes(attributes);
            targetMappings.add(targetMapping);
        }
        return targetMappingRepository.saveAll(targetMappings);
    }

    private List<List<String>> getSourceFieldValues(MultipartFile file, List<String> sourceFields) {
        List<List<String>>  valuesOfEachRow = new ArrayList<>();
        Workbook workbook;
        Sheet sheet;

        try (InputStream data = file.getInputStream()){
            workbook = new XSSFWorkbook(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0 ; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            Row headings = sheet.getRow(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                List<String> valuesInOneRow = new ArrayList<>();

                for(String sourceField : sourceFields){
                    Cell cell = row.getCell(getCellNumber(sourceField, headings));
                    valuesInOneRow.add(String.valueOf(cell));
                }
                valuesOfEachRow.add(valuesInOneRow);
            }
            valuesOfEachRow.remove(0);
        }
        return valuesOfEachRow;
    }

    private int getCellNumber(String sourceField, Row headings) {
        int cellNumber = -1;

        for(int i = 0; i < headings.getLastCellNum(); i++){
            Cell cell = headings.getCell(i);

            if (cell.getStringCellValue().equalsIgnoreCase(sourceField)){
                cellNumber = cell.getColumnIndex();
                break;
            }
        }
        return cellNumber;
    }
}
