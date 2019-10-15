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
