interface Message {
    @SuppressWarnings("SameReturnValue")
    String greet();
}

public class AnonInnerClassAsArgument {

    // method which accepts the object of interface Message
    private void displayMessage(Message m) {
        System.out.println(m.greet() +
                ", This is an example of anonymous inner class as an argument.");
    }

    public static void main(String[] args) {

        // Instantiate Class
        AnonInnerClassAsArgument obj = new AnonInnerClassAsArgument();

        // Pass anonymous inner as arg.
        // (NOTE: This is replaced w/ Lambdas in Java 8)
        //noinspection Convert2Lambda
        obj.displayMessage(new Message() {
            public String greet() {
                return "Hello";
            }
        });

    }
}
