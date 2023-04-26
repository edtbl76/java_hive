public class ThisEx {

    // Using 'this' to differentiate between instance vars and local vars.
    class Student {
        int age;

        Student(int age) {
            this.age = age;
        }
    }

    // Using 'this' for Explicit Constructor Invocation
    class Student2 {
        int age;

        Student2() {
            this(20);
        }

        Student2(int age) {
            this.age = age;
        }
    }


}
