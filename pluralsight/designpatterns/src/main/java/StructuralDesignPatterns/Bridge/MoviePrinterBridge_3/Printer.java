package StructuralDesignPatterns.Bridge.MoviePrinterBridge_3;

import java.util.List;

public abstract class Printer {

    /*
        Heart of the Bridge Pattern
        (COMPOSITION!)
     */
    public String print(Formatter formatter) {
        return formatter.format(getHeader(), getDetails());
    }

    protected abstract List<Detail> getDetails();

    protected abstract String getHeader();
}
