package com.mofinloans.app.spreadsheet.api;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents many worksheets from a single workbook
 *
 * @author jkmalan (John Malandrakis)
 */
public class XWorkbook implements Iterable<XWorksheet> {

    private Map<String, XWorksheet> sheets = new HashMap<>();

    private String bookname;
    private Workbook workbook;

    public XWorkbook(String bookname, Workbook workbook) {
        this.bookname = bookname;
        this.workbook = workbook;

        for (Sheet sheet : workbook) {
            String sheetname = sheet.getSheetName();
            sheets.put(sheetname, new XWorksheet(this, sheetname, sheet));
        }
    }

    public XWorksheet getWorksheet(String sheetname) {
        return sheets.get(sheetname);
    }

    @Override
    public Iterator<XWorksheet> iterator() {
        return sheets.values().iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof XWorkbook)) {
            return false;
        }

        XWorkbook wb = (XWorkbook) obj;
        return bookname.equals(wb.bookname) && workbook.equals(wb.workbook);
    }

}
