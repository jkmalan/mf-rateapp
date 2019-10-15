package com.mofinloans.app;

public interface Configuration {

    public String getString(String key);

    public String getString(String key, String def);

}
