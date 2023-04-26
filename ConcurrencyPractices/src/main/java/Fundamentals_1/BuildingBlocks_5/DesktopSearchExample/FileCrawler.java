package Fundamentals_1.BuildingBlocks_5.DesktopSearchExample;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable {
    private final BlockingQueue<File> files;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> files, FileFilter fileFilter, File root) {
        this.files = files;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry))
                    files.put(entry);
            }
        }
    }

    private boolean alreadyIndexed(final File file) {
        return false;
    }
}
