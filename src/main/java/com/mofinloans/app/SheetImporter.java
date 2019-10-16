package com.mofinloans.app;

import com.mofinloans.app.database.Database;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class SheetImporter {

    private Configuration config;
    private Database database;

    private Workbook workbook;

    public SheetImporter(Configuration config, Database database) {
        this.config = config;
        this.database = database;
        initialize();
    }

    public boolean initialize() {
        try {
            File file = new File(config.getString("wb.path"));
            if (file.exists() && file.isFile() && file.canRead()) {
                FileInputStream stream = new FileInputStream(file);
                this.workbook = new XSSFWorkbook(stream);
                return true;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return false;
    }

    public void loadTables() {
        List<String> adjustments = List.of("ltv-fico", "ltv-dscr");
        for (String table : adjustments) {
            findTable(table);
        }
    }

    public void findTable(String table) {
        String sheetName = config.getString("wb.adj." + table + ".sheet");
        String colHeaderKEY = config.getString("wb.adj." + table + ".col-header");
        String rowHeaderKEY = config.getString("wb.adj." + table + ".row-header");
        Sheet sheet = workbook.getSheet(sheetName);
        CellRangeAddress colHeaderREF = CellRangeAddress.valueOf(colHeaderKEY);
        CellRangeAddress rowHeaderREF = CellRangeAddress.valueOf(rowHeaderKEY);
        for (int rowIndex = colHeaderREF.getFirstRow(); rowIndex < rowHeaderREF.getLastRow(); rowIndex++) {
            Row activeRow = sheet.getRow(rowIndex);
            for (int colIndex = rowHeaderREF.getFirstColumn(); colIndex < colHeaderREF.getLastColumn(); colIndex++) {
                Cell activeCell = activeRow.getCell(colIndex);
                if (rowIndex == colHeaderREF.getFirstRow() || colIndex == rowHeaderREF.getFirstColumn()) {

                }
            }
        }
    }

}
