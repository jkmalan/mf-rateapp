package com.mofinloans.app;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

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
            List<String> sheets = Arrays.asList(/*"Pricer Lookup", "Price Matrix", "Max Price Grid", */"Product Matrix"/*, "Investor Solutions(PI) ADJ", "Rate Calculator", "Investor Solutions(PI) RS"*/);
            for (Sheet sheet : workbook) {
                /* Prime Ascent ADJ: E4 - AJ91 */
                /* Row Headers: D4:D91 */
                /* Col Headers: E3:AJ3 */
                if (sheets.contains(sheet.getSheetName())) {
                    CellRangeAddress range = CellRangeAddress.valueOf("E4:AJ91");
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

                            Row x = sheet.getRow(i);
                            Cell rowTitle = x.getCell(3);
                            builder.append(cellAsString(rowTitle));
                            builder.append(" | ");

                            Row y = sheet.getRow(2);
                            Cell colTitle = y.getCell(j);
                            builder.append(cellAsString(colTitle));
                            builder.append(" | ");

                            builder.append(cellAsString(cell));
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

    public static String cellAsString(Cell cell) {
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
                    o = cell.getCellFormula();
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
