package com.javaoop.Static;

public class StaticClassExample {

    public static void main(String[] args) {
        System.out.println(DataObject.StaticInnerClass.innerStaticVar);
        DataObject.StaticInnerClass.accessOuterClass();
    }

}
