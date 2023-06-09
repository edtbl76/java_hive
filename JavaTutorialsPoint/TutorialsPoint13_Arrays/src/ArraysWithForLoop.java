@SuppressWarnings("ForLoopReplaceableByForEach")
public class ArraysWithForLoop {

    public static void main(String[] args) {

        double[] myList = {1.9, 2.9, 3.4, 3.5};

        // Print all array elements w/ a FOR loop
        for (int i = 0; i < myList.length; i++) {
            System.out.println(myList[i]);
        }

        // ADD EM UP!
        double total = 0;
        for (int i = 0; i < myList.length; i++) {
            total +=myList[i];
        }
        System.out.println("Total is " + total);

        // Get the largest element
        double max = myList[0];
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] > max) max = myList[i];
        }
        System.out.println("Max is " + max);

    }

}
