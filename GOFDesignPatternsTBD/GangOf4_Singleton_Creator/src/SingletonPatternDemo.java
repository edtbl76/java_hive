public class SingletonPatternDemo {

    public static void main(String[] args) {

        // illegal construct
        // Compile Time Error: the constructor SingleObject() is not visble
        // SingleObject object = new SingleObject();

        // Get only available object
        SingleObject object = SingleObject.getInstance();

        // show the message "Do the thing!"
        object.showMessage();
    }
}
