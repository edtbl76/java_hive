public class StaticNestedClassEx {
    static class NestedDemo {
        void myMethod() {
            System.out.println("This is my nested class!");
        }
    }

    public static void main(String[] args) {
        StaticNestedClassEx.NestedDemo nested = new StaticNestedClassEx.NestedDemo();
        nested.myMethod();
    }
}
