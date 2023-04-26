public class ByteExample {
    public static void main(String[] args) {

        /*
            This is more of a demonstration about signed types than it is bytes. However, its probably more
            noteworthy when using bytes, because of the reduced range of values.


         */
        byte almostOverflow = 126;

        String prefix = "Byte Value: ";

        System.out.println(prefix + almostOverflow);

        // This won't.
        System.out.print("Increment Byte...");
        almostOverflow++;
        System.out.println(prefix + almostOverflow);

        // This will overflow to -128
        System.out.print("Increment Byte...");
        almostOverflow++;
        System.out.println(prefix + almostOverflow);
    }
}
