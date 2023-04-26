package com.javaoop.Static;

/*
    normal IMPORT declaration imports classes from packages so that they can  be used w/o the package reference

    STATIC IMPORT
        - special declaration used for importing STATIC MEMBERS from classes and allowing them to be used w/o class references

        TYPES:
            SINGLE-STATIC IMPORT
                - imports one static from member from a type

                EX:
                    import static << package name >>.<< type name >>.<<  static member name>>;

            STATIC-IMPORT-ON-DEMAND
                - imports ALL STATIC MEMBERS of a given type

                EX:
                    import static << package name >>.<<type name>>.*;
 */
import static java.lang.System.out;


public class StaticImportExample {

    public static void main(String[] args) {
        DataObject.staticVar = 30;
        out.println(DataObject.staticVar);

    }

}

