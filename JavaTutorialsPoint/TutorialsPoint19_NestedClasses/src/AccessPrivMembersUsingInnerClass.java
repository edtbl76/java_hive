class OuterDemo1 {

    // private var of outer class
    @SuppressWarnings("FieldCanBeLocal")
    private int num = 175;

    // inner class
    class InnerDemo2 {
        int getNum() {
            System.out.println("This gets the private member of the outer class!");
            return num;
        }
    }
}

public class AccessPrivMembersUsingInnerClass {

    public static void main(String[] args) {
        // Instantiate the Outer Class
        OuterDemo1 o = new OuterDemo1();

        // Instantiate the inner class
        OuterDemo1.InnerDemo2 i = o.new InnerDemo2();
        System.out.println(i.getNum());

    }
}
