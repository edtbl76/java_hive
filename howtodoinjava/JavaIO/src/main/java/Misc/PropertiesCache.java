package Misc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesCache {

    private final Properties config = new Properties();

    private PropertiesCache() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.properties");
        System.out.println("Read All Properties From File");
        try {
            config.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // SINGLETON
    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return config.getProperty(key);
    }

    public void setProperty(String key, String value) {
        config.setProperty(key, value);
    }

    public Set<String> getAllPropertyNames() {
        return config.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return config.containsKey(key);
    }
}
