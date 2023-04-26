import java.util.Arrays;

public class CharPrimitive {

    public static void main(String[] args) {

        // Normal Char
        char ch = 'a';
        System.out.println("I'm a normal character:  " + ch);

        //  Unicode Char
        char unich = '\u039A';
        System.out.println("I'm a unicode character: " + unich);

        //I'm an array of characters
        char []chars = {'a', 'r', 'r', 'a', 'y'};
        System.out.println("I'm an array. Java Code Assemble! " + Arrays.toString(chars));
    }
}
