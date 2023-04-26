package CreationalDesignPatterns.Singleton.LazyLoaded_2;

public class DbSingletonDemo {

    public static void main(String[] args) {
        DbSingleton instance = DbSingleton.getInstance();
        System.out.println(instance);

        DbSingleton anotherInstance = DbSingleton.getInstance();
        System.out.println(anotherInstance);

        System.out.println("These are the same instances");
    }

}
