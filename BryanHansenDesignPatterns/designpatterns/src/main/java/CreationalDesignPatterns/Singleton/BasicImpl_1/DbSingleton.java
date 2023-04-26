package CreationalDesignPatterns.Singleton.BasicImpl_1;

public class DbSingleton {

    /*
        This is eagerly loaded because static variables are always created when the class is loaded.
     */
    private static DbSingleton instance = new DbSingleton();

    /*
        prevents others from instantiating this object using the 'new' keyword.
     */
    private DbSingleton() {

    }

    /*
        Standard accessor for a singleton.
     */
    public static DbSingleton getInstance() {
        return instance;
    }
}
