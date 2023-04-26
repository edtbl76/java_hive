public class IIBWithInheritance {

    public static void main(String[] args) {
        Inherited inherited = new Inherited();
    }
}

@SuppressWarnings("ALL")
class Base {

    {
        System.out.println("Base IIB");
    }

    Base() {
        System.out.println("Base-Constructor Called");
    }
}
@SuppressWarnings("ALL")
class Inherited extends Base {

    {
        System.out.println("Inherited IIB");
    }

    Inherited() {
        super();
        System.out.println("Inherited-Constructor Called");
    }
}

