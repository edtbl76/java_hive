@SuppressWarnings("StringBufferMayBeStringBuilder")
public class UsingStringBuffer2 {

    public static void main(String[] args) {

        /*
            Continuation of StringBuffer Examples.

            In this class

            - getChars()
            - indexOf()
            - lastIndexOf()
            - charAt()
            - setCharAt()
            - IntStream chars()
            - setLength()
            - substring()
            - subsequence()


         */
        StringBuffer stringBuffer = new StringBuffer("Gravy the dog");
        System.out.println("Original: " + stringBuffer);


        /*
            Get Chars
         */
        char[] name = new char[10];
        stringBuffer.getChars(0, 5, name, 0);

        System.out.print("New Char Array: ");
        for (char c : name) {
            System.out.print(c);
        }
        System.out.println("\n");

        /*
            getChars() part 2
                - this OVERWRITES existing elements in the array, it doesn't displace them



         */
        StringBuffer fun = new StringBuffer("Meat Nougat");
        System.out.println("Silly: " + fun);
        fun.getChars(1,4, name, 2);
        System.out.print("Edited Char Array: ");
        for (char c : name) {
            System.out.print(c);
        }
        System.out.println("\n");


        /*
            Getting the index of the specified character.
         */
        StringBuffer myName = new StringBuffer("Edward");
        System.out.println("Let's look for 'd's in " + myName);
        int first = myName.indexOf("d");
        System.out.println("indexOf stops at the first occurence: " + first);
        int second = myName.indexOf("d", 2);
        System.out.println("unless you specify a starting point: " + second);
        System.out.println("The last 'd' is " + myName.lastIndexOf("d"));



        /*
            Get/Set Character At
         */
        for (int i = 0; i < myName.length(); i++) {
            Character current = myName.charAt(i);
            int asciiNew = myName.charAt(i) + 1;
            myName.setCharAt(i, (char)asciiNew);
            System.out.println(current + " is being changed to " + (char)asciiNew);
        }

        /*
            chars() outputs an IntStream of the ASCII characters.
            (We can use map to make this easier than the previous function...)

         */
        myName.chars().forEach(element -> System.out.print((char)element));
        System.out.println("\n");

        int previousLength = myName.length();
        myName.setLength(3);
        System.out.println("Setting length shorter will truncate your string " + myName);
        myName.setLength(previousLength);
        System.out.println("Restoring it doesn't restore your chars "+ myName);

        /*
            Demonstrates how to create substrings from a longer StringBuffer.
                - substring returns String objects
                    - only requires start parameter. w/o the end, it will simply create a substring from start to the
                    length of the string.
                - subSequence returns CharSequence
                    - requires start AND end
         */
        StringBuffer monkey = new StringBuffer("Michael Xavier Mangini");
        System.out.println("My Monkey: " + monkey);
        System.out.println("\tFirstName  : " + monkey.substring(0, 7));
        System.out.println("\tMiddleName : " + monkey.substring(8, 14));
        System.out.println("\tLastName   : " + monkey.substring(15));
        System.out.println(monkey.subSequence(0, 7));

    }

}
