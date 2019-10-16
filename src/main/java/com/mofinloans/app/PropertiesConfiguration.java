package com.mofinloans.app;

import java.util.Properties;

public class PropertiesConfiguration implements Configuration {

    private Properties properties = new Properties();
    private String settings = "settings.properties";

    public PropertiesConfiguration() {
        // TODO Properties loading from file
        properties.put("db.host", "localhost");
        properties.put("db.port", "3306");
        properties.put("db.name", "mf_ratetool");
        properties.put("db.user", "mofinloans");
        properties.put("db.pass", "mofinloans");

        properties.put("wb.path", "");
        properties.put("wb.defaults.base-rate", "102.0");
        properties.put("wb.defaults.base-price", "0.0595");
        properties.put("wb.defaults.base-scalar", "0.0");
        properties.put("wb.defaults.base-margin", "0.0");
        properties.put("wb.adj.ltv-fico.sheet", "Investor Solutions(PI) AFJ");
        properties.put("wb.adj.ltv-fico.col-header", "C32:J32");
        properties.put("wb.adj.ltv-fico.row-header", "A33:A41");
        properties.put("wb.adj.ltv-dscr.sheet", "Investor Solutions(PI) AFJ");
        properties.put("wb.adj.ltv-dscr.col-header", "C43:J43");
        properties.put("wb.adj.ltv-dscr.row-header", "A44:A46");
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
