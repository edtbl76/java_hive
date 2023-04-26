package CreationalDesignPatterns.Singleton.LazyLoaded_2;

public class DbSingleton {

    private static DbSingleton instance = null;

    /*
        prevents others from instantiating this object using the 'new' keyword.
     */
    private DbSingleton() {

    }

    /*
        Standard accessor for a singleton.
     */
    public static DbSingleton getInstance() {
        /*
            This null check introduces lazy loaded
            - perf improvement, because objects aren't created during class loading.
            - we also only instantiate if we use it. (more economic use of resources)
         */
        if (instance == null)
            instance = new DbSingleton();
        return instance;
    }
}
