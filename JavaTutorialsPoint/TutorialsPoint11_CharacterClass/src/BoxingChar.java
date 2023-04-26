public class BoxingChar {

    private static char test(char c) {
        return c;
    }

    public static void main(String[] args) {
        // Boxing a char
        Character ch = 'a';
        System.out.println(ch);

        //primitive 'x' is boxed for method test, but it is returned UNBOXED to char 'c'
        char c = test('x');
        System.out.println(c);
    }
}
