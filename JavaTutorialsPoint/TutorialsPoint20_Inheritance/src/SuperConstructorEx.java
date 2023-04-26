class Dad3 {
    int age;

    Dad3(int age) {
        this.age = age;
    }

    public void getAge() {
        System.out.println("The value of age in super class is " + age);
    }
}

public class SuperConstructorEx extends Dad3{

    SuperConstructorEx(int age) {
        // This is invoking the parent class constructor.
        super(age);
    }

    public static void main(String[] args) {
        SuperConstructorEx s = new SuperConstructorEx(24);
        s.getAge();
    }
}
