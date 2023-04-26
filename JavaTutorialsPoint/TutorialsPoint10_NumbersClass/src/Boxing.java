public class Boxing {

    public static void main(String[] args) {
        Integer x = 5; // this boxes an 'int' into an Integer object.
                        // (Actually this tells the compiler to do it!)
        x = x + 10; // This unboxes the Integer back to an int
        System.out.println(x);
    }
}
