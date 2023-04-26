public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        //Image is loaded from disk
        image.display();
        System.out.println();

        // Second call won't load from disk, because the realImage object already exists.
        image.display();
    }
}
