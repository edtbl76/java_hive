class Dad2 {
    int num = 20;

    public void display() {
        System.out.println("This is the display method of superclass.");
    }
}

public class SuperKWEx extends Dad2 {
    private int num = 10;

    // display method of sub class
    public void display() {
        System.out.println("This is the display method of subclass.");
    }

    private void myMethod() {
        SuperKWEx sub = new SuperKWEx();
        sub.display();
        super.display();
        System.out.println("Subclass Variable: " + sub.num);
        System.out.println("Superclass Variable: " + super.num);
    }

    public static void main(String[] args) {
        SuperKWEx obj = new SuperKWEx();
        obj.myMethod();
    }
}
