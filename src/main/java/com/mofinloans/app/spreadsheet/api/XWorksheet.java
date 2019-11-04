package com.mofinloans.app.spreadsheet.api;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a single worksheet from a single workbook
 *
 * @author jkmalan (John Malandrakis)
 */
public class XWorksheet implements Iterable<XCell> {

    private XWorkbook workbook;

    private Map<String, XCell> cells = new HashMap<>();

    private String sheetname;
    private Sheet worksheet;

    public XWorksheet(XWorkbook workbook, String sheetname, Sheet worksheet) {
        this.sheetname = sheetname;
        this.worksheet = worksheet;

        for (Row row : worksheet) {
            for (Cell cell : row) {
                XCellData data = new XCellData(this, cell);
                int rowIdx = cell.getRowIndex();
                int colIdx = cell.getColumnIndex();
                cells.put(cell.getAddress().formatAsString(), new XCell(rowIdx, colIdx, data));
            }
        }
    }

    public XWorkbook getWorkbook() {
        return workbook;
    }

    public XRange getRange(XCell start, XCell end) {
        return null;
    }

    public XCell getCell(int row, int col) {
        return null;
    }

    @Override
    public Iterator<XCell> iterator() {
        return cells.values().iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof XWorksheet)) {
            return false;
        }

        XWorksheet ws = (XWorksheet) obj;
        return workbook.equals(ws.workbook) && sheetname.equals(ws.sheetname);
    }

}
