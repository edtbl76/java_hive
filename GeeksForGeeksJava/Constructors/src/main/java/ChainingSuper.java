class CSBase {
    String name;

    CSBase() {
        this("");
        System.out.println("No-args constructor of Base");
    }

    CSBase(String name) {
        this.name = name;
        System.out.println("Parameterized constructor of Base");
    }
}

class CSDerived extends CSBase {

    CSDerived() {
        System.out.println("No-args constructor of Derived");
    }

    CSDerived(String name) {
        super(name);
        System.out.println("Parameterized constructor of Derived");
    }
}

public class ChainingSuper {
    public static void main(String[] args) {
        CSDerived csDerived = new CSDerived("SuperDuper!");
    }
}
