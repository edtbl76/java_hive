public class StringEx {

    public static void main(String[] args) {
        System.out.println("I am a string literal!");

        String lit = "I'm still a string literal";
        System.out.println(lit);

        //Call from constructor
        char[] chars = {'a', 'r', 'r', 'a', 'y'};
        String s = new String(chars);
        System.out.println("I am a String that was created from a constructor that accepted an array of chars: " + s);
    }
}
