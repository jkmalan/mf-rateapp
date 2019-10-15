package com.mofinloans.app;

import com.mofinloans.app.database.Database;
import com.mofinloans.app.spreadsheet.SheetManager;

public class RateEngine {

    private static RateEngine ENGINE;

    private String host = "localhost";
    private String port = "3306";
    private String name = "mf_ratetool";
    private String user = "mofinloans";
    private String pass = "mofinloans";

    public static RateEngine getEngine() {
        if (ENGINE == null)
            ENGINE = new RateEngine();
        return ENGINE;
    }

    private Database database;
    private SheetManager spreadsheet;

    private RateEngine() {
        this.database = new Database(host, port, name, user, pass);
        this.spreadsheet = new SheetManager("C:\\Users\\John\\Downloads\\rentalpricing (2).xlsx");
    }

    public Database getDatabase() {
        return database;
    }

    public SheetManager getSpreadsheet() {
        return spreadsheet;
    }

}
