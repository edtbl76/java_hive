class MySingleton {

    static MySingleton instance = null;
    public int number = 10;

    private MySingleton() {}

    static public MySingleton getInstance() {
        if (instance == null)
            instance = new MySingleton();
        return instance;
    }
}


public class PrivateConstructorSingletonExample {
    public static void main(String[] args) {
        MySingleton mySingleton1 = MySingleton.getInstance();
        MySingleton mySingleton2 = MySingleton.getInstance();

        mySingleton1.number += 10;

        // Proves that there is only a single instance
        System.out.println("Value of mySingleton1.number: " + mySingleton1.number);
        System.out.println("Value of mySingleton2.number: " + mySingleton2.number);

    }
}
