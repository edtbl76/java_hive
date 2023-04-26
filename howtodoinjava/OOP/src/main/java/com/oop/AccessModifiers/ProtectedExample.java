package com.oop.AccessModifiers;

public class ProtectedExample {

    private String word;

    protected String getWord() {
        return this.word;
    }

    protected void setWord(String word) {
        this.word = word;
    }
}

class DerivedClass extends ProtectedExample {

    DerivedClass(String word) {
        super.setWord(word);
    }

    @Override
    public String getWord() {
        return super.getWord();
    }

    @Override
    public void setWord(String word) {
        super.setWord(word);
    }
}

class Main {

    public static void main(String[] args) {
        // The methods of the derived class are public, but the parent class methods are protected (only available
        // to the  subclass that are part of the same package). This is fairly contrived, but whatever.
        DerivedClass dc = new DerivedClass("word");
        System.out.println(dc.getWord());

    }
}