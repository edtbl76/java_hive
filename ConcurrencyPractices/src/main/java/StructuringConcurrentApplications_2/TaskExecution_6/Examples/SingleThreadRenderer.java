package StructuringConcurrentApplications_2.TaskExecution_6.Examples;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadRenderer {
    void renderPage(CharSequence charSequence) {
        renderText(charSequence);
        List<ImageData> imageData = new ArrayList<>();

        // Get the references
        for (ImageInfo imageInfo : scanForImageInfo(charSequence)) {
            imageData.add(imageInfo.downloadImage());
        }


        // Render them.
        for (ImageData data : imageData) {
            renderImage(data);
        }
    }

    // Just placeholders before this so you don't have to stare at ugly red warnings.
    private void renderImage(ImageData data) {
    }

    private ImageInfo[] scanForImageInfo(CharSequence charSequence) {
        return new ImageInfo[10];
    }

    private void renderText(CharSequence charSequence) {
    }

    private class ImageData {
    }

    private class ImageInfo {

        public ImageData downloadImage() {
            return new ImageData();
        }
    }
}
