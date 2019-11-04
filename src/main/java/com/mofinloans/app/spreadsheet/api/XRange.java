package com.mofinloans.app.spreadsheet.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an array of cells from a single worksheet from a single workbook
 *
 * @author jkmalan (John Malandrakis)
 */
public class XRange implements Iterable<XCell> {

    private XWorkbook workbook;
    private XWorksheet worksheet;

    private List<XCell> cells = new ArrayList<>();

    public XRange(XRangeAddress address) {

    }

    @Override
    public Iterator<XCell> iterator() {
        return cells.iterator();
    }

}
