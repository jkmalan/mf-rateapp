package com.mofinloans.app;

import com.mofinloans.app.database.Database;

public class RateEngine implements Engine {

    private Configuration configuration;
    private Database database;
    private SheetImporter importer;
    private Calculator calculator;

    public RateEngine() {
        this.configuration = new PropertiesConfiguration();
        this.database = new Database(configuration);
        this.importer = new SheetImporter(configuration, database);
        this.calculator = new Calculator(database);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Database getDatabase() {
        return database;
    }

    public SheetImporter getImporter() {
        return importer;
    }

    public Calculator getCalculator() {
        return calculator;
    }

}
