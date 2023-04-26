package com.java7.changes.Watcher;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class ConfigChangeListener implements Runnable {

    private String configFileName = null;
    private String fullFilePath = null;

    public ConfigChangeListener(final String filePath) {
        this.fullFilePath = filePath;
    }

    public void run() {
        try {
            register(this.fullFilePath);
        } catch (IOException ioe ) {
            ioe.printStackTrace();
        }
    }

    private void register(final String file) throws IOException {
        final int lastIndex = file.lastIndexOf("/");
        String dirPath = file.substring(0, lastIndex + 1);
        String fileName = file.substring(lastIndex + 1, file.length());
        this.configFileName = fileName;

        configurationChanged(file);
        startWatcher(dirPath, fileName);
    }

    private void startWatcher(String dirPath, String file) throws IOException {
        final WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(dirPath);
        path.register(watchService, ENTRY_MODIFY);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    watchService.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });

        WatchKey key = null;
        while (true) {
            try {
                /*
                    take() vs. poll()
                    - take() waits for the next change to happen and until then, it is blocked
                    - poll() immediately checks for a change event.
                        - returns null if nothing has changed since the last poll()
                        - doesn't block exec, so it should be called in a Thread, w/ some sleep time.
                 */
                key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.context().toString().equals(configFileName)) {
                        configurationChanged(dirPath + file);
                    }
                }
                boolean reset = key.reset();
                if (!reset) {
                    System.out.println("Could not reset the watch key");
                    break;
                }
            } catch (Exception e) {
                System.out.println("InterruptedException: " + e.getMessage());
            }
        }
    }

    public void configurationChanged(final String file) {
        System.out.println("Refreshing the configuration.");
        AppConfig.getInstance().initialize(file);
    }
}
