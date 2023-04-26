/*
Example of using local variables
 */
public class LocalVariableExample {

    private void pupAge() {
        int age = 0;
        age = age + 7;
        System.out.println("Puppy age is: " + age);
    }

    public static void main(String[] args) {
        LocalVariableExample lve = new LocalVariableExample();
        lve.pupAge();
    }
}
