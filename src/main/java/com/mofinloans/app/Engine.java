package com.mofinloans.app;

import com.mofinloans.app.database.Database;

public interface Engine {

    public Configuration getConfiguration();

    public Database getDatabase();

    public SheetImporter getImporter();

    public Calculator getCalculator();

}
