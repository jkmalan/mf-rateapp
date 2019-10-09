package com.mofinloans.app;

import com.mofinloans.app.database.Database;
import com.mofinloans.app.spreadsheet.Spreadsheet;

public class RateEngine {

    private static RateEngine ENGINE;

    public static RateEngine getEngine() {
        if (ENGINE == null)
            ENGINE = new RateEngine();
        return ENGINE;
    }

    private Database database;
    private Spreadsheet spreadsheet;

    private RateEngine() {
        this.database = new Database();
        this.spreadsheet = new Spreadsheet("C:\\Users\\John\\Downloads\\rentalpricing (2).xlsx");
    }

    public Database getDatabase() {
        return database;
    }

    public Spreadsheet getSpreadsheet() {
        return spreadsheet;
    }


}
