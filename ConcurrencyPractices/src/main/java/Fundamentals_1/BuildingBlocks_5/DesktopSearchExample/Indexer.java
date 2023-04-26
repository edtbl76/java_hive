package Fundamentals_1.BuildingBlocks_5.DesktopSearchExample;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {
    private final BlockingQueue<File> files;

    public Indexer(BlockingQueue<File> files) {
        this.files = files;
    }

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(files.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void indexFile(final File file) {
        // blah
    }

}
