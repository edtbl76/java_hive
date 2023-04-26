public class PrivateInnerClassEx {

    // inner class
    private class InnerDemo {
        void print() {
            System.out.println("This is an inner class!");
        }
    }

    // accessing inner class from inside the method
    private void displayInner() {
        InnerDemo inner = new InnerDemo();
        inner.print();
    }

    public static void main(String[] args) {
        PrivateInnerClassEx pice = new PrivateInnerClassEx();
        pice.displayInner();
    }
}
