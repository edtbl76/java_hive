/*
This is an example of Calling Class Variables from outside of a class to show
the fully qualified name.

NOTE: I don't have to instantiate the class to get access to the variable. It's JUST THERE,
because it is stored in memory when the PROGRAM starts.
 */
public class ClassVarExTest {

    public static void main(String[] args) {
        System.out.println("I work at " + ClassVarEx.DEPARTMENT);
    }

}
