package com.ideas2it.censusmigration.util.helper;

import com.ideas2it.EhrFileOperations.model.SourceExcelRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileReadHelper {

    //check if file is of Excel type or not
    public static boolean checkFileFormat(MultipartFile file) {
        return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    //converts excel to list of Patients
    public static List<SourceExcelRecord> excelRowsToDbRecordsConverter(MultipartFile file) {
        List<SourceExcelRecord> records = new ArrayList<>();

        try {
            InputStream inputStream = file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            for (Sheet sheet: workbook) {
                List<String> headers = new ArrayList<>();

                for (Row row : sheet) {
                    Iterator<Cell> cellIterator = row.iterator();

                    if (row.getRowNum() == 0) {
                        while (cellIterator.hasNext()) {
                            String cellValue = String.valueOf(cellIterator.next());
                            headers.add(cellValue);
                        }
                    } else {
                        Map<String, Object> patientAttributes = new HashMap<>();
                        for (String header : headers) {
                            Cell cell = cellIterator.next();
                            String cellValue = String.valueOf(cell);
                            patientAttributes.put(header, cellValue);
                        }
                        SourceExcelRecord excelRecord = new SourceExcelRecord();
                        excelRecord.setFileName(file.getOriginalFilename());
                        excelRecord.setRowNumber(row.getRowNum());
                        excelRecord.setSheetName(sheet.getSheetName());
                        excelRecord.setPatientAttributes(patientAttributes);
                        records.add(excelRecord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

//    public static List<SourceExcelRecord> excelRowsToDbRecordsConverter(MultipartFile file) {
//        List<SourceExcelRecord> records = new ArrayList<>();
//
//        XSSFWorkbook workbook = null;
//        try {
//            InputStream inputStream = file.getInputStream();
//            workbook = new XSSFWorkbook(inputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//            Sheet sheet = workbook.getSheetAt(i);
//            List<String> headers = new ArrayList<>();
//            if(sheet.iterator().hasNext()){
//                for (Row row : sheet) {
//                    Iterator<Cell> cellIterator = row.cellIterator();
//                    if (row.getRowNum() == 0) {
//                        while (cellIterator.hasNext()) {
//                            String cellValue = String.valueOf(cellIterator.next());
//                            headers.add(cellValue);
//                        }
//
//                    } else {
//                        Map<String, Object> patientAttributes = new HashMap<>();
//                        for (String header : headers) {
//                                Cell cell = cellIterator.next();
//                                String cellValue = String.valueOf(cell);
//                                patientAttributes.put(header, cellValue);
//                        }
//                        SourceExcelRecord sourceExcelRecord = new SourceExcelRecord();
//                        sourceExcelRecord.setFileName(file.getOriginalFilename());
//                        sourceExcelRecord.setRowNumber(row.getRowNum());
//                        sourceExcelRecord.setSheetName(sheet.getSheetName());
//                        sourceExcelRecord.setPatientAttributes(patientAttributes);
//                        records.add(sourceExcelRecord);
//                    }
//                }
//            }
//        }
//        return records;
//    }
}

