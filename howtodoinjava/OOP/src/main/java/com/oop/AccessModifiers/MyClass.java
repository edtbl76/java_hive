package com.oop.AccessModifiers;

public class MyClass {

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public static void main(String[] args) {

        // the FIELD (word) is private, so I can't access it.

        // However the METHODS are public and I can access them.
        MyClass mpc = new MyClass();
        mpc.setWord("word");
        System.out.println(mpc.getWord());

        /*
            NOTE: I'm not creating an example for private access...its in every one of the examples. 
         */
    }
}
