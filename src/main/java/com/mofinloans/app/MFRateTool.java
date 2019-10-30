package com.mofinloans.app;

import com.mofinloans.app.database.Database;
import com.mofinloans.app.spreadsheet.SheetManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

public class MFRateTool {

    public static void main(String[] args) {
        Engine engine = new RateEngine();
        double ltv = 66.00;
        double fico = 690.00;
        double dscr = 0.90;
        double reserve = 1.00;
        double balance = 400000.00;
        String purpose = "PUR";
        String state = "CA";
        String property = "SFR";
        String amortization = "";
        int prepayment = 0;
        String term = "30 YR FIX";

        BigDecimal rate = new BigDecimal(0.05950);

        BigDecimal adjFico = engine.getCalculator().calculateFicoAdjust(ltv, fico);
        System.out.println("LTV vs. FICO: " + adjFico);
        rate = rate.add(adjFico);

        BigDecimal adjDscr = engine.getCalculator().calculateDscrAdjust(ltv, dscr);
        System.out.println("LTV vs. DSCR: " + adjDscr);
        rate = rate.add(adjDscr);

        BigDecimal adjReserve = engine.getCalculator().calculateReserveAdjust(ltv, reserve);
        System.out.println("LTV vs. Reserves: " + adjReserve);
        rate = rate.add(adjReserve);

        BigDecimal adjBalance = engine.getCalculator().calculateBalanceAdjust(ltv, balance);
        System.out.println("LTV vs. Balance: " + adjBalance);
        rate = rate.add(adjBalance);

        BigDecimal adjPurpose = engine.getCalculator().calculatePurposeAdjust(ltv, purpose);
        System.out.println("LTV vs. Purpose: " + adjPurpose);
        rate = rate.add(adjPurpose);

        BigDecimal adjState = engine.getCalculator().calculateStateAdjust(ltv, state);
        System.out.println("LTV vs. State: " + adjState);
        rate = rate.add(adjState);

        BigDecimal adjProperty = engine.getCalculator().calculatePropertyAdjust(ltv, property);
        System.out.println("LTV vs. Property: " + adjProperty);
        rate = rate.add(adjProperty);

        BigDecimal adjAmortization = engine.getCalculator().calculateAmortizationAdjust(ltv, amortization);
        System.out.println("LTV vs. Amortization: " + adjAmortization);
        rate = rate.add(adjAmortization);

        BigDecimal adjPrepayment = engine.getCalculator().calculatePrepaymentAdjust(ltv, prepayment);
        System.out.println("LTV vs. Prepayment: " + adjPrepayment);
        rate = rate.add(adjPrepayment);

        BigDecimal adjTerm = engine.getCalculator().calculateTermAdjust(ltv, term);
        System.out.println("Term: " + adjTerm);
        rate = rate.add(adjTerm);

        System.out.println("Estimated Rate: " + rate.round(new MathContext(4)));


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
