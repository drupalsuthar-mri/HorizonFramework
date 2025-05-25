package com.mri.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelData {

    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) throws IOException, FileNotFoundException {
        List<Map<String, String>> testData = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.getRow(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();

            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                String header = headerRow.getCell(j).getStringCellValue();
                String value = "";

                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            value = String.valueOf((int) cell.getNumericCellValue()); // or use DecimalFormat
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case BLANK:
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }
                }
                rowData.put(header, value);
            }
            testData.add(rowData);
        }

        workbook.close();
        fis.close();
        return testData;
    }

}
