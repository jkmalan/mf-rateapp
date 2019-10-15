package com.mofinloans.app;

import com.mofinloans.app.database.Database;
import com.mofinloans.app.spreadsheet.SheetManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class MFRateTool {

    public static void main(String[] args) {
        Engine engine = new RateEngine();
        BigDecimal rate = engine.getCalculator().calculateBaseRate(63, 702);
        System.out.println(rate);


        /* Old Code, worked fine but ugly af, so rebuilding it all, probably doesnt work anymore
        RateCalculator rc = new RateCalculator(engine.getDatabase());
        BigDecimal rate = rc.getBaseRate(63.0, 701.0);
        System.out.println(rate);

        boolean run = false;
        if (run) {
            try {
                FileOutputStream stream = new FileOutputStream(new File("./output.txt"));
                SheetManager sheetMgr = null;
                Database database = engine.getDatabase();

                sheetMgr.setSheet("Product Matrix");
                CellRangeAddress range = CellRangeAddress.valueOf("E4:AJ91");
                CellRangeAddress rangeRowTitle = CellRangeAddress.valueOf("D4:D91");
                CellRangeAddress rangeColTitle = CellRangeAddress.valueOf("E3:AJ3");
                for (int rowIndex = range.getFirstRow(); rowIndex <= range.getLastRow(); rowIndex++) {
                    for (int colIndex = range.getFirstColumn(); colIndex < range.getLastColumn(); colIndex++) {
                        Cell cell = sheetMgr.getSheet().getRow(rowIndex).getCell(colIndex);
                        System.out.println(rowIndex + ", " + colIndex + ", " + cell + ", ");
                        Cell rowTitle = sheetMgr.getRowTitle(rangeRowTitle, cell.getAddress());
                        Cell colTitle = sheetMgr.getColTitle(rangeColTitle, cell.getAddress());
                        String[] columns = (sheetMgr.getString(rowTitle, true) +
                                " | " + sheetMgr.getString(colTitle, true) +
                                " | " + sheetMgr.getString(cell, true)).split("( *[|] *)");

                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        */
    }

}
