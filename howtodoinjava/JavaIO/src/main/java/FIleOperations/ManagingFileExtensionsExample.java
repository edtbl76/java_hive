package FIleOperations;

import java.io.File;
import java.io.FilenameFilter;

public class ManagingFileExtensionsExample {

    public static void main(String[] args) {

        String target = "tempIdea";
        File dir = new File(target);

        String[] xmlfiles = dir.list(new XMLFilter());

        if (xmlfiles.length == 0)
            return;

        for(String xml: xmlfiles) {
            String temp = target + File.separator + xml;
            boolean isDeleted = new File(temp).delete();
            System.out.println("file: " + temp + " is deleted: " + isDeleted);
        }

    }
}

class XMLFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".xml");
    }
}