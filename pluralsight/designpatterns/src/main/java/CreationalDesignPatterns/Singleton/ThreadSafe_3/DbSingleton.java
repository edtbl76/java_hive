package CreationalDesignPatterns.Singleton.ThreadSafe_3;

public class DbSingleton {

    /*
        volatile is used to make the instance thread safe.
     */
    private static volatile DbSingleton instance = null;

    /*
        prevents others from instantiating this object using the 'new' keyword.
     */
    private DbSingleton() {
        /*
            Prevents creation of new instance via reflection
         */
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    /*
        Standard accessor for a singleton.
        - avoid make entire class ALWAYS synchronized due to being a performance hit
        - only synchronize on creation.
     */
    public static DbSingleton getInstance() {
        /*
            This null check introduces lazy loaded
         */
        if (instance == null) {
            // DoubleLocking Check, used for providing thread safety (standard lazy load is not thread safe)
            synchronized (DbSingleton.class) {
                if (instance == null) {
                    instance = new DbSingleton();
                }
            }

        }
        return instance;
    }
}
