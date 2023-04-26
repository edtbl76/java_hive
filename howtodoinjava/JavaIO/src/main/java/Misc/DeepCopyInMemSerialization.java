package Misc;

public class DeepCopyInMemSerialization {
    /*
        Easiest way to perform deep cloning
            - SERIALIZATION
                - In Java, this means Object -> bytes -> back to Object
        NOTE: Deep Cloning + Singleton = BAD
            - it makes it possible to have multiple instances of the singleton
     */

    public static void main(String[] args) throws Exception {

        SerializableClass mine = new SerializableClass("Ed", "Mangini");
        System.out.println(mine);

        SerializableClass deep = mine.deepCopy();
        System.out.println(deep);
    }
}

