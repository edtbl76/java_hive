public class ArraysWithForeachLoop {

    public static void main(String[] args) {
        double[] myList = {1.9, 2.9, 3.4, 3.5};

        // Print the array elements.
        // Foreach is the same thing as the ENHANCED FOR LOOP
        double total = 0;
        double max = myList[0];
        for (double element : myList) {
            System.out.println(element);
            total += element;
            if ( element > max ) max = element;
        }
        System.out.println("Total is " + total + "\nMax is " + max);
    }
}
