public class thisExample2 {

    // instance variable
    private int num = 10;

    private thisExample2() {
        System.out.println("This is an example program on keyword this");
    }

    private thisExample2(int num) {
        //invoking default constructor
        this();

        // assigning local var 'num' to instance var 'num'
        this.num = num;
    }

    private void greet() {
        System.out.println("Hi Welcome to Tutorialspoint");
    }

    private void print() {

        // Local Variable num
        int num = 20;

        // Print local var
        System.out.println("value of local variable num is : "+num);

        // Print inst var
        System.out.println("value of instance variable num is : "+this.num);

        // Invoke Greet
        this.greet();
    }

    public static void main(String[] args) {

        // Instantiating the class and print it
        thisExample2 obj1 = new thisExample2();
        obj1.print();

        // Do it again w/ a parameter
        thisExample2 obj2 = new thisExample2(30);
        obj2.print();
    }
}
