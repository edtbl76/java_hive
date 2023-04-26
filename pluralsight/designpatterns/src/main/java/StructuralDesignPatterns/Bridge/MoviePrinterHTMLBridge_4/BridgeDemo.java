package StructuralDesignPatterns.Bridge.MoviePrinterHTMLBridge_4;

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

        /*
            We were able to create a new formatter that provides HTML content
            without having to change the moviePrinter.

            We simply passed the moviePrinter to it, and voila... it works.

            Object composition is powerful.
         */
        Formatter htmlFormatter = new HtmlFormatter();
        String htmlMaterial = moviePrinter.print(htmlFormatter);
        System.out.println(htmlMaterial);

    }
}
