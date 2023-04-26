@SuppressWarnings("StringBufferMayBeStringBuilder")
public class StringBufferEnsureCapacity {

    public static void main(String[] args) {

                /*
            We can set the capacity after the fact.

            NOTE: This isn't very intuitive.
            - The result is going to be set to the LARGER of
                - IF we specify a value <= existing capacity, it is ignored (0 - 16 for default)
                - While the value we specify is <= (2N + 2), the result will be 2N + 2 (17-34 for default)
                - values larger than 2N + 2 will be set exactly
                    - this only happens once, because we have a new capacity.


         */
        StringBuffer stringBuffer = new StringBuffer("Hi");
        for (int i = 0; i <= 50; i++) {
            stringBuffer.ensureCapacity(i);
            System.out.println("No: " + i + " Capacity: " + stringBuffer.capacity());
        }
    }
}
