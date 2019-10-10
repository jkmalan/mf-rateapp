package com.mofinloans.app.spreadsheet;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SheetManager {

    private Workbook workbook;
    private Sheet sheet;

    public SheetManager(String filename) {
        try {
            File file = new File(filename);
            if (file.exists() && file.isFile() && file.canRead()) {
                FileInputStream stream = new FileInputStream(file);
                this.workbook = new XSSFWorkbook(stream);
                this.sheet = workbook.getSheetAt(0);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setSheet(String sheetname) {
        Sheet sheet = workbook.getSheet(sheetname);
        if (sheet != null) {
            this.sheet = sheet;
        }
    }

    public Sheet getSheet() {
        return sheet;
    }

    public List<Cell> getCells(CellRangeAddress range) {
        List<Cell> cells = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < range.getFirstRow(); rowIndex++) {
            for (int colIndex = 0; colIndex < range.getFirstColumn(); colIndex++) {
                cells.add(sheet.getRow(rowIndex).getCell(colIndex));
            }
        }
        return cells;
    }

    public Cell getRowTitle(CellRangeAddress range, CellAddress address) {
        return sheet.getRow(address.getRow()).getCell(range.getFirstColumn());
    }

    public Cell getColTitle(CellRangeAddress range, CellAddress address) {
        return sheet.getRow(range.getFirstRow()).getCell(address.getColumn());
    }

    public String getString(Cell cell, boolean evaluate) {
        DataFormatter formatter = new DataFormatter();
        String value = null;
        if (evaluate) {
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            value = formatter.formatCellValue(cell, evaluator);
        } else {
            value = formatter.formatCellValue(cell);
        }
        return value;
    }

}
