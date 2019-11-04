package com.mofinloans.app.spreadsheet.api;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Generate, manipulate, and populates workbooks, worksheets, and ranges
 *
 * @author jkmalan (John Malandrakis)
 */
public class XManager implements Iterable<XWorkbook> {

    private Map<String, XWorkbook> books = new HashMap<>();

    public XManager() {

    }

    public void loadWorkbook(String path, String bookname) {
        try {
            File file = new File(path);
            if (file.exists() && file.isFile() && file.canRead()) {
                FileInputStream stream = new FileInputStream(file);
                XWorkbook workbook = new XWorkbook(bookname, new XSSFWorkbook(stream));
                books.put(bookname, workbook);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public XWorkbook getWorkbook(String bookname) {
        return books.get(bookname);
    }

    public XWorksheet getWorksheet(String bookname, String sheetname) {
        return getWorkbook(bookname).getWorksheet(sheetname);
    }

    public XRange getRange(String bookname, String sheetname, XCell start, XCell end) {
        return getWorkbook(bookname).getWorksheet(sheetname).getRange(start, end);
    }

    public XCell getCell(String bookname, String sheetname, int row, int col) {
        return getWorkbook(bookname).getWorksheet(sheetname).getCell(row, col);
    }

    @Override
    public Iterator<XWorkbook> iterator() {
        return books.values().iterator();
    }

}
