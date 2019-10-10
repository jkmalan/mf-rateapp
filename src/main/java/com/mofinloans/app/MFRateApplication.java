package com.mofinloans.app;

import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MFRateApplication {

    public static void main(String[] args) {
        RateEngine engine = RateEngine.getEngine();

        try {
            List<List<String>> lookupTable = new ArrayList<>();
            System.out.println(new File(".").getAbsolutePath());
            FileOutputStream stream = new FileOutputStream(new File("./output.txt"));
            StringBuilder builder = new StringBuilder();

            Workbook workbook = engine.getSpreadsheet().getWorkbook();
            int cells = 0;
            List<String> sheets = Arrays.asList(/*"Pricer Lookup", "Product Matrix", "Max Price Grid", */"Price Matrix"/*, "Investor Solutions(PI) ADJ", "Rate Calculator", "Investor Solutions(PI) RS"*/);
            for (Sheet sheet : workbook) {
                /* Prime Ascent ADJ: E4 - AJ91 */
                /* Row Headers: D4:D91 */
                /* Col Headers: E3:AJ3 */
                if (sheets.contains(sheet.getSheetName())) {
                    CellRangeAddress range = CellRangeAddress.valueOf("C21:AZ363");
                    System.out.println(range.formatAsString());
                    System.out.println(range.getFirstRow());
                    System.out.println(range.getLastRow());
                    System.out.println(range.getFirstColumn());
                    System.out.println(range.getLastColumn());
                    System.out.println("SHEET: " + sheet.getSheetName());
                    for (int i = range.getFirstRow(); i <= range.getLastRow(); i++) {
                        Row row = sheet.getRow(i);
                        for (int j = range.getFirstColumn(); j <= range.getLastColumn(); j++) {
                            Cell cell = row.getCell(j);

                            String result = findInTable(sheet, "B21:B363", "C20:AZ20", cell.getAddress().formatAsString());
                            System.out.println(result);
                            builder.append(result);
                            builder.append(" | ");
                            builder.append(cellAsString(cell, true));
                            builder.append("\n");
                            cells++;
                        }
                    }
                }
            }
            System.out.println("CELL COUNT: " + cells);
            stream.write(builder.toString().getBytes());
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String findInTable(Sheet sheet, String rowTitleRefArray, String colTitleRefArray, String cell) {
        CellReference reference = new CellReference(cell);
        int rowIndex = reference.getRow();
        int colIndex = reference.getCol();
        System.out.println("Reference: " + reference.formatAsString());
        System.out.println("Row: " + rowIndex + ", Col: " + colIndex);

        CellRangeAddress rowRange = CellRangeAddress.valueOf(rowTitleRefArray);
        System.out.println("RowTitleRef: " + rowRange.formatAsString());
        Row rowOne = sheet.getRow(rowIndex);
        Cell rowTitle = rowOne.getCell(rowRange.getFirstColumn());
        CellRangeAddress colRange = CellRangeAddress.valueOf(colTitleRefArray);
        System.out.println("RowTitleRef: " + colRange.formatAsString());
        Row rowTwo = sheet.getRow(colRange.getFirstRow());
        Cell colTitle = rowTwo.getCell(colIndex);

        return cellAsString(rowTitle, true) + " | " + cellAsString(colTitle, true);
    }

    public static String cellAsString(Cell cell, boolean evaluate) {
        Object o = "";
        if (cell != null) {
            CellType type = cell.getCellType();
            switch (type) {
                case STRING:
                    o = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    o = cell.getNumericCellValue();
                    break;
                case BOOLEAN:
                    o = cell.getBooleanCellValue();
                    break;
                case FORMULA:
                    FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                    CellValue value = evaluator.evaluate(cell);
                    o = evaluate ? value.formatAsString() : cell.getCellFormula();
                    break;
                case ERROR:
                    o = cell.getErrorCellValue();
                    break;
                case BLANK:
                    o = "POI_BLANK";
                    break;
                case _NONE:
                    o = "POI_NONE";
                    break;
                default:
                    o = "POI_OTHER";
                    break;
            }
        }

        return o.toString();
    }

}
