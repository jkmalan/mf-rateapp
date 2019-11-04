package com.mofinloans.app.spreadsheet.api;

/**
 * Represents a single cell from a single worksheet from a single workbook
 *
 * @author jkmalan (John Malandrakis)
 */
public class XCell {

    private XWorkbook workbook;
    private XWorksheet worksheet;

    private int row;
    private int col;
    private XCellData data;

    public XCell(int row, int col, XCellData data) {
        this.row = row;
        this.col = col;
        this.data = data;
    }

    public XWorkbook getWorkbook() {
        return workbook;
    }

    public XWorksheet getWorksheet() {
        return worksheet;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public XCellData getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof XCell)) {
            return false;
        }

        XCell cell = (XCell) obj;
        return workbook.equals(cell.workbook) && worksheet.equals(cell.worksheet) && row == cell.row && col == cell.col;
    }

}
