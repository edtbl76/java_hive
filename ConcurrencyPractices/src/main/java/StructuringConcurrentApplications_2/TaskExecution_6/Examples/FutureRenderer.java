package StructuringConcurrentApplications_2.TaskExecution_6.Examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureRenderer {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);

/*
        // Using this to show the obfuscated anonymous function, which shows
        the solution in greater detail

        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };*/

        // Lambdified Callable
        Callable<List<ImageData>> task = () -> {
            List<ImageData> result = new ArrayList<>();
            for (ImageInfo imageInfo : imageInfos) {
                result.add(imageInfo.downloadImage());
            }
            return result;
        };

        /*
            Submit wraps a callable into a future.
         */
        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);


        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            // Reassert thread's interrupted status
            Thread.currentThread().interrupt();

            // We don't need result, so cancel the task
            future.cancel(true);
        } catch (ExecutionException e) {
            e.getCause();
        }

    }


    // Stubs just so you don't have to look at ugly errors
    private void renderImage(ImageData data) {
    }

    private void renderText(CharSequence source) {
    }


    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return new ArrayList<>();
    }

    private class ImageInfo {
        public ImageData downloadImage() {
            return new ImageData();
        }
    }

    private class ImageData {
    }
}
