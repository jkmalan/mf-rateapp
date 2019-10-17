package com.mofinloans.app;

import com.mofinloans.app.database.Database;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
            findTable("", "", "");
        }
    }

    public void findTable(String sheetKEY, String rowHeaderKEY, String colHeaderKEY) {
        String query =
                "INSERT INTO adj_ltv_vs_fico (bkt_ltv, bkt_fico, rate_adj) " +
                        "VALUES (?, ?, ?);";
        PreparedStatement statement = database.prepare(query);

        Sheet sheet = workbook.getSheet(sheetKEY);
        CellRangeAddress colHeaderREF = CellRangeAddress.valueOf(colHeaderKEY);
        CellRangeAddress rowHeaderREF = CellRangeAddress.valueOf(rowHeaderKEY);
        for (int rowIndex = rowHeaderREF.getFirstRow(); rowIndex <= rowHeaderREF.getLastRow(); rowIndex++) {
            Row rowTitleCOL = sheet.getRow(rowIndex);
            Row colTitleROW = sheet.getRow(colHeaderREF.getFirstRow());
            Cell rowTitleCELL = rowTitleCOL.getCell(rowHeaderREF.getFirstColumn());
            for (int colIndex = colHeaderREF.getFirstColumn(); colIndex <= colHeaderREF.getLastColumn(); colIndex++) {
                Cell colTitleCELL = colTitleROW.getCell(colIndex);
                Cell cell = rowTitleCOL.getCell(colIndex);

                Map<Integer, String> entries = new HashMap<>();
                entries.put(1, getString(rowTitleCELL, true));
                entries.put(2, getString(colTitleCELL, true));
                entries.put(3, getString(cell, true));
            }
        }
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
