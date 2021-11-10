package com.obsqura.utility;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {

    public static HSSFWorkbook wb;
    public static HSSFSheet sh;
    public static FileInputStream f;

    public List<String> readExcel(String excel, String sheetName) throws IOException {

        String path = System.getProperty("user.dir")+ File.separator+ excel;
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

      /*  public List<String> readExcel(String file_path, String sheet) throws IOException {
        f = new FileInputStream(System.getProperty("user.dir") +File.separator+ file_path);
        wb = new HSSFWorkbook(f);
        sh = wb.getSheet(sheet);
        List<String> ExcelRows = new ArrayList<String>();
        int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
        for (int i = 0; i <= rowCount; i++) {
            Row row = sh.getRow(i);
            int cellCount = row.getLastCellNum();
            for (int j = 0; j < cellCount; j++) {
                if(row.getCell(j).getCellType() == CellType.NUMERIC) {
                    ExcelRows.add(NumberToTextConverter.toText(row.getCell(j).getNumericCellValue()));
                }
            }
        }
        return ExcelRows;
    }*/
}
