@SuppressWarnings({"InstantiationOfUtilityClass", "AccessStaticViaInstance"})
public class CallByValue {

    public static void main(String[] args) {

        int first = 10;
        int second = 15;

        // Create an Instance of our example
        Example object = new Example();
        System.out.println("first: " + first + " second: " + second);

        // Pass variables into the class function
        object.Call(first, second);

        // print the values.
        System.out.println("first: " + first + " second: " + second);
    }

}

class Example {

    /*
       Example function to change the value of the parameters
    */
    @SuppressWarnings("UnusedAssignment")
    public static void Call(int x, int y) {
        x++;
        y++;
    }

}
