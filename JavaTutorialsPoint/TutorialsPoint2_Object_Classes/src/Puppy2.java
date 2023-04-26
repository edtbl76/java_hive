/*
    Example of implementing a constructor
 */


@SuppressWarnings({"SameParameterValue", "unused"})
public class Puppy2 {

    private Puppy2(String name) {
        // This constructor has one parameter, name.
        System.out.println("Passed Name is :" + name);
    }

    public static void main(String[] args) {
        // Following statement would create an object object myPuppy
        Puppy2 myPuppy = new Puppy2( "Gizmo ");
    }
}
