package com.emangini.java11.StringAPI;

public class StringStripExample {

    public static void main(String[] args) {
        String withSpaces = "      hi       ";
        System.out.println("[" + withSpaces.strip() +"]");
        System.out.println("[" + withSpaces.stripLeading() +"]");
        System.out.println("[" + withSpaces.stripTrailing() +"]");
    }
}
