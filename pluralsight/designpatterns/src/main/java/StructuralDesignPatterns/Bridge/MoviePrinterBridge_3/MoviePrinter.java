package StructuralDesignPatterns.Bridge.MoviePrinterBridge_3;

import java.util.ArrayList;
import java.util.List;

/*
    1/2 of bridge id here

 */
public class MoviePrinter extends Printer {

    /*
        Composition! This is where the composition comes into play
     */
    private Movie movie;

    public MoviePrinter(Movie movie) {
        this.movie = movie;
    }

    @Override
    protected List<Detail> getDetails() {
        List<Detail> details = new ArrayList<>();

        details.add(new Detail("Title", movie.getTitle()));
        details.add(new Detail("Year", movie.getYear()));
        details.add(new Detail("Runtime", movie.getRuntime()));

        return details;
    }

    @Override
    protected String getHeader() {
        return movie.getClassification();
    }
}
