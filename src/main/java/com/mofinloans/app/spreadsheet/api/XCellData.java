package com.mofinloans.app.spreadsheet.api;

import org.apache.poi.ss.usermodel.Cell;

public class XCellData {

    private XWorkbook workbook;
    private XWorksheet worksheet;
    private Cell cell;

    public XCellData(XWorksheet worksheet, Cell cell) {
        this.workbook = worksheet.getWorkbook();
        this.worksheet = worksheet;
        this.cell = cell;
    }

}
