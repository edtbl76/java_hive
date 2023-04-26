package StructuralDesignPatterns.Bridge.MoviePrinterBridge_3;

public class BridgeDemo {

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setClassification("Horror");
        movie.setTitle("The Exorcist");
        movie.setRuntime("2:00");
        movie.setYear("1974");

        /*
            These two methods can change independently.
            They know nothing of each other directly.
         */
        Formatter printFormatter = new PrintFormatter();
        Printer moviePrinter = new MoviePrinter(movie);

        String printedMaterial = moviePrinter.print(printFormatter);
        System.out.println(printedMaterial);

    }
}
