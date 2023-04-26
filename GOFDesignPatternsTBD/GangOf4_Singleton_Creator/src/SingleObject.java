public class SingleObject {

    // create an object of Single Object
    private static final SingleObject instance = new SingleObject();

    // make the constructor private so that class can't be instantiated
    private SingleObject() {}

    // Get the only object available
    public static SingleObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
