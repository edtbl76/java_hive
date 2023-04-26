public class MethodLocalInnerClassEx {

    // instance method of outer class
    private void myMethod() {
        int num = 23;

        // Method-Local Inner Class
        class MethodLocalInnerDemo {
            private void print() {
                System.out.println("This is method inner class " + num);
            }
        } //end of inner class

        // Accessing Inner class (Have to do it in the method, because it has local scope only)
        MethodLocalInnerDemo mlid = new MethodLocalInnerDemo();
        mlid.print();
    }

    public static void main(String[] args) {
        MethodLocalInnerClassEx mlice = new MethodLocalInnerClassEx();
        mlice.myMethod();
    }


}
