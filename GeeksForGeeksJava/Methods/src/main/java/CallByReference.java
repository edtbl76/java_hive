public class CallByReference {
    public static void main(String[] args) {

        /*
            Create an instance of our class
            - assign values via constructor
         */
        Reference reference = new Reference(10,20);
        System.out.println("a: " + reference.a + " b: " + reference.b);

        /*
            changing the values
         */
        reference.Mutate(reference);
        System.out.println("a: " + reference.a + " b: " + reference.b);


    }
}

class Reference {

    int a;
    int b;

    Reference(int x, int y) {
        a = x;
        b = y;
    }

    void Mutate(Reference reference) {
        reference.a += 10;
        reference.b += 20;
    }
}
