package com.java8.WatchService;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class RegisteringWatchServiceExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get(".");
        WatchService watchService = path.getFileSystem().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        /*
            This key remains valid until
                - it is cancelled explicitly by invoking the cancel() method
                - cancelled implicitly because  the object is no longer accessible
                - closing the watch service
         */
        WatchKey watchKey = null;
        while (true)  {
            watchKey  = watchService.poll(10, TimeUnit.MINUTES);
            if (watchKey != null) {
                watchKey.pollEvents().forEach(event -> System.out.println(event.context()));
            }
            watchKey.reset();
        }
    }
}
