package CreationalPatterns.GoF.Singleton.EagerInitialization3;

public class Launcher {

    public static void main(String[] args) {

        /*
            Look carefully at the output when you execute this.

            When we call runJob(), it creates an instance of the class, but since it's a void, we aren't
            returned a reference (we've created an anonymous reference).

            This creates an instance of the class, but we don't actually have any intention of using it, we
            just needed to use the method.

            This demonstrates one of the drawbacks of Eager initialization... we end up with objects loaded in
            memory that we might not care about, soaking up resources that we might need for other purposes.

            This lack of isolation between the "Singleton" and other methods is a limitation, but Example 4
            proposes a solution...
         */
        System.out.println("Trying to create a singleton!");
        EagerSingleton.runJob();
    }



}
