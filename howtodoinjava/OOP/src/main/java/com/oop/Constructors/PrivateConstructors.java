package com.oop.Constructors;

import java.io.Serializable;

/*
    This example is to demonstrate the use of PrivateConstructors which are used for implementing the Singleton pattern

        1.) we create a default constructor and set it to PRIVATE. This means that no one can call it unless they are in
        the SAME CLASS.

        2.) we create a static inner class.
            - the static keyword ensures that there is only ONE copy of the inner class, i.e. it is initialized at program start
            and there is only ONE SHARED COPY
            - since we are in the same class, we can access the private constructor, so we create an immutable instance of
            the constructor.

            NOTE: this is really the power of Singleton. private constructor + static inner classes.

        3.) We create a public method enabling access to the Singleton.
 */
public class PrivateConstructors implements Serializable  {

    private static final long serialVersionUID = 1L;

    private PrivateConstructors() {} // private constructor

    private static class SingletonHolder {
        public static final PrivateConstructors INSTANCE = new PrivateConstructors();
    }

    public static PrivateConstructors getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(PrivateConstructors.getInstance());
    }
}
