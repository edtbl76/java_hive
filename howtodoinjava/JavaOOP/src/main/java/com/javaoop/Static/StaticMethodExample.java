package com.javaoop.Static;

import javax.xml.crypto.Data;

public class StaticMethodExample {

    /*
        STATIC METHOD

            ACCESS_MODIFIER static RETURN_TYPE METHOD_NAME;

        NOTE:
            - You can access ONLY STATIC VARIABLES INSIDE STATIC METHODS.
                - if you attempt to access non-static vars in a static  method, it blows up
            - static methods can be accessed via its class reference
                - there is no need to create an instance of class
                (Although you are more than welcome to if it  makes you feel better)
            - static methods have CLASS LEVEL SCOPE
     */
    public static void main(String[] args) {
        DataObject.staticVar = 30;
        Integer value1 = DataObject.getStaticVar(); // access w/ class reference

        DataObject thisIsDumb = new DataObject();
        Integer value2 = thisIsDumb.getStaticVar();

        System.out.println(value1);
        System.out.println(value2);

    }
}

