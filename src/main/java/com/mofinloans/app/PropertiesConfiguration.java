package com.mofinloans.app;

import java.util.Properties;

public class PropertiesConfiguration implements Configuration {

    private Properties properties = new Properties();
    private String settings = "settings.properties";

    public PropertiesConfiguration() {
        // TODO Properties loading from file
        properties.setProperty("db.host", "localhost");
        properties.setProperty("db.port", "3306");
        properties.setProperty("db.name", "mf_ratetool");
        properties.setProperty("db.user", "mofinloans");
        properties.setProperty("db.pass", "mofinloans");

        properties.setProperty("wb.path", "");
        properties.setProperty("wb.defaults.base-rate", "102.0");
        properties.setProperty("wb.defaults.base-price", "0.0595");
        properties.setProperty("wb.defaults.base-scalar", "0.0");
        properties.setProperty("wb.defaults.base-margin", "0.0");
        properties.setProperty("wb.adjustments", "0.0");
        properties.setProperty("wb.adj.ltv-fico.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj.ltv-fico.col-header", "C32:J32");
        properties.setProperty("wb.adj.ltv-fico.row-header", "A33:A41");
        properties.setProperty("wb.adj.ltv-dscr.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj.ltv-dscr.col-header", "C43:J43");
        properties.setProperty("wb.adj.ltv-dscr.row-header", "A44:A46");
    }

    @Override
    public String getString(String key) {
        return properties.getProperty(key);
    }

    @Override
    public String getString(String key, String def) {
        return properties.getProperty(key, def);
    }

}
