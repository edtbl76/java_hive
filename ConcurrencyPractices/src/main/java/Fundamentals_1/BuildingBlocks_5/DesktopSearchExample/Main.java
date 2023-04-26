package Fundamentals_1.BuildingBlocks_5.DesktopSearchExample;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    private static final int BOUND = 10;
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void startIndexing(final File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        final FileFilter filter = pathname -> true;

        for (final File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }

}
