package com.oop.AccessModifiers;

public class DefaultAccess {

    private String word = "default";

    DefaultAccess() {}

    DefaultAccess(String  word) {
        this.word = word;
    }

    void setWord(String word) {
        this.word = word;
    }

    protected String getWord() {
        return word;
    }

    void print() {
        System.out.println(this.word);
    }

}

class DAMain {

    public static void main(String[] args) {
        /*
            I created a package-private print() method as a weakly thought-out example of a way to leverage package private.
         */
        DefaultAccess da = new DefaultAccess();
        da.print();

        da.setWord("Not Default");
        da.print();

    }
}

