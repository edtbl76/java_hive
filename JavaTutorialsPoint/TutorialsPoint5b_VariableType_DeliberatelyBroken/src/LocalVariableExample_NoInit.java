/*
    See LocalVariableExample.

    This is the same class, w/o initialization of the variable.
    (this cant' compile as a result, because there is no value to update)

    You should see "java: variable age might not have been initialized"
 */

public class LocalVariableExample_NoInit {

    @SuppressWarnings("unused")
    private void pupAge() {
        int age;
        //age = age + 7; // <--- this can't work!

        System.out.println("Got you sucka! I can't print the variable, because it ain't gots no value, son!");
    }

    public static void main(String[] args) {
        LocalVariableExample_NoInit lve_ni = new LocalVariableExample_NoInit();
        lve_ni.pupAge();
    }
}
