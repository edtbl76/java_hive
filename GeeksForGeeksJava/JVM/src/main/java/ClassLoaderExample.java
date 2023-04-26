public class ClassLoaderExample {
    public static void main(String[] args) {

        /*
            String is loaded by the bootstrap loader.
            Since the bootstrap loader "precedes" objects, it can't be an object .
            Therefore this outputs 'null'
         */
        System.out.println(String.class.getClassLoader());

        /*
            This class is loaded by the Application Class Loader
         */
        System.out.println(ClassLoaderExample.class.getClassLoader());
    }
}
