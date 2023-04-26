/*
    Example of access instance variables and methods of a class using getters and setters
 */
@SuppressWarnings("SameParameterValue")
public class Puppy3 {
    private int puppyAge;

    private Puppy3(String name) {
        System.out.println("Name: " + name);
    }

    private void setPuppyAge( int age ) { puppyAge = age; }

    private int getPuppyAge() {
        return puppyAge;
    }

    public static void main(String[] args) {
        // create the object
        Puppy3 myPup = new Puppy3( " Gizmo" );

        // Setter for age
        myPup.setPuppyAge( 7 );

        // Get it!
        int age =  myPup.getPuppyAge();
        System.out.println("My Puppy is " + age + " years old.");

        // Access it directly
        System.out.println("Variable puppyAge: " + myPup.puppyAge);
    }
}
