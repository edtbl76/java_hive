package com.basics.LabeledStatements;

public class LabeledStatementsExample {

    public static void main(String[] args) {

        /*
            These are usually covered as part of Control Statements. I removed it because I consider Labeled Statements
            a plague upon all houses, and a tumor that should be removed from the language.

            I hate this for the same reason that I hate jump and goto statements. It makes code hard to read, and a pain in the
            ass to debug.
            NOTE: most documentation considers this a logical facsimilie of goto statements. Fuck goto.

         */

        loop: for(int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                System.out.println("In if block :: " + i);
                continue loop;
            } else {
                System.out.println("In else block :: " + i);
            }
        }
    }
}
