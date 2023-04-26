package com.concurrency.AdvancedConcurrency.ThreadLocalVariables;

import java.util.stream.IntStream;

public class TLVMain {

    public static void main(String[] args) {

        /*
            The order will vary here.
            - We're launching three threads, that will finish at random times.
            - however you can line them up by ensuring that the thread # matches up w/ the time.
         */
        IntStream.range(1, 4).forEach(value -> new Thread(new DemoTask()).start());
    }
}
