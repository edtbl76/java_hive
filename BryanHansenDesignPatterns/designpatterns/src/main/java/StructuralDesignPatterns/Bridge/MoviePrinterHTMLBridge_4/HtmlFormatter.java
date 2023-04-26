package StructuralDesignPatterns.Bridge.MoviePrinterHTMLBridge_4;

import java.util.List;

/*
    1/2 of bridge pattern
 */
public class HtmlFormatter implements Formatter {
    @Override
    public String format(String header, List<Detail> details) {
       StringBuilder builder = new StringBuilder();
       builder
               .append("<table>")
               .append("<th>Classification</th>")
               .append("<th>")
               .append(header)
               .append("</th>");

       details.forEach(
               detail -> builder
                       .append("<tr><td>")
                       .append(detail.getLabel())
                       .append("</td><td>")
                       .append(detail.getValue())
                       .append("</td><tr>"));
       builder.append("</table>");

       return builder.toString();
    }
}
