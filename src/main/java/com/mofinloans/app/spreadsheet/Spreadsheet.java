package com.mofinloans.app.spreadsheet;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Spreadsheet {

    private Workbook workbook;

    public Spreadsheet(String filename) {
        try {
            File file = new File(filename);
            if (file.exists() && file.isFile() && file.canRead()) {
                FileInputStream stream = new FileInputStream(file);
                this.workbook = new XSSFWorkbook(stream);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public Sheet getSheet(String sheetname) {
        return workbook.getSheet(sheetname);
    }

}
