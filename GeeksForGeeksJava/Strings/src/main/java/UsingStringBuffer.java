@SuppressWarnings("StringBufferMayBeStringBuilder")
public class UsingStringBuffer {

    public static void main(String[] args) {

        /*
            Empty String Buffer of default size (16 characters)
            - we can initialize it by passing the value to the constructor.
            - we can also override the default size and pass that as an int to the constructor
                - (This might be useful if we know we're going to init a lot of string sthat are larger than 16 chars)


            This covers
            - length()
            - capacity()
            - append()
            - insert()
            - reverse()
            - delete()
            - deleteCharAt()
            - replace()
         */
        StringBuffer stringBuffer = new StringBuffer();


        /*
            Append allows me to add to the end of a string.
            (In this case, there is nothing to add to, so i'm starting the string).
         */
        stringBuffer.append("Ed Mangini");
        System.out.println(stringBuffer + ":\n\tLength: "
                + stringBuffer.length() + "\n\tCapacity: "
                + stringBuffer.capacity());

        /*
            Insertion takes an index to start inserting and a char or String to insert at.
         */
        stringBuffer.insert(2, "ward");
        System.out.println(stringBuffer + ":\n\tLength: "
                + stringBuffer.length() + "\n\tCapacity: "
                + stringBuffer.capacity());

        /*
            This is a good way to cheat on a lot of coding interviews
         */
        stringBuffer.reverse();
        System.out.println(stringBuffer + ":\n\tLength: "
                + stringBuffer.length() + "\n\tCapacity: "
                + stringBuffer.capacity());

        /*
            I can delete a character(s)
         */
        stringBuffer.reverse();
        for (int i = 0; i < 4; i++) {
            stringBuffer.deleteCharAt(2);
        }
        System.out.println(stringBuffer + ":\n\tLength: "
                + stringBuffer.length() + "\n\tCapacity: "
                + stringBuffer.capacity());

        /*
            ... or a range

            END indexes are EXCLUSIVE
         */
        stringBuffer.delete(0, 3);
        System.out.println(stringBuffer + ":\n\tLength: "
                + stringBuffer.length() + "\n\tCapacity: "
                + stringBuffer.capacity());


        /*
            I can specify a start/finish index of a portion of text I want
            to replace.

            END indexes are EXCLUSIVE
         */
        stringBuffer.replace(3, 7, "ly");
        System.out.println(stringBuffer + ":\n\tLength: "
                + stringBuffer.length() + "\n\tCapacity: "
                + stringBuffer.capacity());




    }
}
