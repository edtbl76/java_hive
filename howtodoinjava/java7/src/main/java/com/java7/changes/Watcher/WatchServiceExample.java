package com.java7.changes.Watcher;

public class WatchServiceExample {

    /*
        WATCH SERVICE
            - JDK internal service which watches for changes on registered objects.
            - registered objects are instances of WATCHABLE interface.
            - when registering objects, we must specify the type of change events to be monitored.
     */

    private static final String FILE_PATH = "./test.properties";
    public static void main(String[] args) {
        ConfigChangeListener listner = new ConfigChangeListener(FILE_PATH);
        try {
            new Thread(listner).start();
            while (true) {
                Thread.sleep(2000L);
                System.out.println(AppConfig.getInstance().getConfiguration("TEST_KEY"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
