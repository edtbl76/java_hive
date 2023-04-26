import sun.java2d.pipe.SpanShapeRenderer;

import java.util.*;
import java.text.*;


public class SimpleDateFormatEx {

    public static void main(String[] args) {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("Current Date: " + ft.format(now));
    }
}
