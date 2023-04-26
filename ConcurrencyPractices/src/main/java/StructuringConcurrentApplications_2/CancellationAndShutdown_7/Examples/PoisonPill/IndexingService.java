package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples.PoisonPill;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class IndexingService {

    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    private void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }


    // Consumer Thread for IndexingService
    private class IndexerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON) {
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException consumed) { }
        }

        private void indexFile(File file) {
        }
    }

    // Producer Thread for IndexingService
    private class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException ie1) {
                // fall through to finally block
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                    } catch (InterruptedException ie2) {
                        // retry has to happen here.
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            // crawl, bitch!
        }
    }
}
