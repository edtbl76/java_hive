public class FinalMethod {

    // I made this static as a short cut so I could access it directly from main.
    // USE ACCESSORS!
    private static String name = "Gizmo";

    @SuppressWarnings("SameParameterValue")
    private void changeName(String newName){
        name = newName;
    }

    public static void main(String[] args) {
        // Prints out the original value
        System.out.println("Name: " + name);

        // Instantiate the class so we can get access to the method.
        FinalMethod fm = new FinalMethod();
        fm.changeName("NotGizmo");

        // new value.
        System.out.println("Name: " + name);

    }



}
