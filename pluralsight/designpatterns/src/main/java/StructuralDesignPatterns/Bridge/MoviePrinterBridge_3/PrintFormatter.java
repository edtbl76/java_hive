package StructuralDesignPatterns.Bridge.MoviePrinterBridge_3;

import java.util.List;

/*
    1/2 of bridge pattern
 */
public class PrintFormatter implements Formatter {
    @Override
    public String format(String header, List<Detail> details) {
        StringBuilder builder = new StringBuilder();
        builder.append(header).append("\n");

        details.forEach(
                detail -> builder
                        .append(detail.getLabel())
                        .append(":")
                        .append(detail.getValue())
                        .append("\n"));

        return builder.toString();
    }
}
