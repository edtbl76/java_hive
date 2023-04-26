package com.java7.changes.Watcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private final static AppConfig INSTANCE = new AppConfig();

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    private static Properties configuration = new Properties();
    private static Properties getConfiguration() {
        return configuration;
    }

    public void initialize(final String file) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(file));
            configuration.load(in);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String getConfiguration(final String key) {
        return (String) getConfiguration().get(key);
    }

    public String getConfigurationWithDefaultValue(final String key, final String defaultValue) {
        return (String) getConfiguration().getProperty(key, defaultValue);
    }
}


