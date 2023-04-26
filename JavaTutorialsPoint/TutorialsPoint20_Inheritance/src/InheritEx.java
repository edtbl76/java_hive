class Dad {
    int z;

    void addition(int x, int y) {
        z = x + y;
        System.out.println("The sum of the given numbers: " + z);
    }

    void subtraction(int x, int y) {
        z = x - y;
        System.out.println("The difference between the given numbers: " + z);
    }
}

public class InheritEx extends Dad{
    private void multiplication(int x, int y) {
        z = x * y;
        System.out.println("The product of the given numbers:" + z);
    }

    public static void main(String[] args) {
        int a = 20, b = 10;
        InheritEx demo = new InheritEx();
        demo.addition(a, b);
        demo.subtraction(a, b);
        demo.multiplication(a, b);
    }

}

