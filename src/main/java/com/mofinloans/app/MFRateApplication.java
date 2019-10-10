package com.mofinloans.app;

import com.mofinloans.app.spreadsheet.SheetManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MFRateApplication {

    public static void main(String[] args) {
        try {
            FileOutputStream stream = new FileOutputStream(new File("./output.txt"));
            StringBuilder builder = new StringBuilder();

            int cells = 0;
            SheetManager sheetMgr = RateEngine.getEngine().getSpreadsheet();
            CellRangeAddress range = CellRangeAddress.valueOf("E4:AJ91");
            CellRangeAddress rangeRowTitle = CellRangeAddress.valueOf("D4:D91");
            CellRangeAddress rangeColTitle = CellRangeAddress.valueOf("E3:AJ3");
            for (int rowIndex = range.getFirstRow(); rowIndex <= range.getLastRow(); rowIndex++) {
                for (int colIndex = range.getFirstColumn(); colIndex < range.getLastColumn(); colIndex++) {
                    Cell cell = sheetMgr.getSheet().getRow(rowIndex).getCell(colIndex);
                    Cell rowTitle = sheetMgr.getRowTitle(rangeRowTitle, cell.getAddress());
                    Cell colTitle = sheetMgr.getColTitle(rangeColTitle, cell.getAddress());
                    builder.append(sheetMgr.getString(rowTitle, true));
                    builder.append(" | ");
                    builder.append(sheetMgr.getString(colTitle, true));
                    builder.append(" | ");
                    builder.append(sheetMgr.getString(cell, true));
                    builder.append("\n");
                }
            }
            System.out.println("CELL COUNT: " + cells);
            stream.write(builder.toString().getBytes());
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
