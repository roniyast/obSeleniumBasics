package com.obsqura.utility;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {

    public List<String> readExcel(String excel , String sheetName) throws IOException {
        String path="H:\\Automation Testing\\Assignments\\Demo\\"+excel;
        List<String> list = new ArrayList<String>();
        DataFormatter formatter = new DataFormatter();
        FileInputStream file = new FileInputStream(path);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet(sheetName);
        for (Row r : sheet) {
            for (Cell c : r) {
                list.add(formatter.formatCellValue(c));
            }
        }
        return list;
    }
}
