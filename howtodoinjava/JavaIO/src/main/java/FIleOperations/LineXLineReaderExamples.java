package FIleOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class LineXLineReaderExamples {

    public static void main(String[] args) throws IOException {
        java8Stream();
        readLineFileReader();
        java8StreamBetter();

    }

    private static void java8Stream() throws IOException {

        /*
            These two blocks are separated, because the Stream is CLOSED in the try-with-resources block.
            This means I have to create a new try-with-resources block for each stream??? Poppycock!
         */
        try (Stream<String> lines = Files.lines(Paths.get("pom.xml"))) {

            Optional<String> hasDep = lines.filter(s -> s.contains("dependency")).findFirst();
            hasDep.ifPresent(System.out::println);
        }

        // this yields no results... deliberately
        try (Stream<String> lines = Files.lines(Paths.get("pom.xml"))) {
            Optional<String> hasBooger = lines.filter(s -> s.contains("booger")).findFirst();
            hasBooger.ifPresent(System.out::println);

        }
    }

    // this sucks. Overly verbose.
    private static void readLineFileReader() throws IOException {
        File file = new File("pom.xml");

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("dependency")) {
                    System.out.println(line);
                }
            }
        }
    }

    private static void java8StreamBetter() throws IOException {

        Path path = Paths.get("pom.xml");

        /*
        This is better because it closes both the STREAM and the UNDERLYING FILE as well.
         */
        try(Stream<String> filtered = Files.lines(path)
            // test if file is closed or not
            .onClose(() -> System.out.println("File closed"))
            .filter(s -> s.contains("dependency"))) {

            // This is optional because it may be empty.
            Optional<String> hasDependency = filtered.findFirst();
            hasDependency.ifPresent(System.out::println);
        }
    }


}
