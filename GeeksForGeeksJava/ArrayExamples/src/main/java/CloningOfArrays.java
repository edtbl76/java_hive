public class CloningOfArrays {
    public static void main(String[] args) {

        /*
            Create an array, clone it, and then perform a test for equality.

            This is false, because a DEEP COPY is performed.
            - this means that the new data structure contains COPIES of the members of the cloned structure rather
            than references.
                - since they are copied, they have new memory locations.
         */
        int[] arrayOfInts = {2, 4, 6};
        int[] cloneArray = arrayOfInts.clone();
        System.out.println("arrayOfInts == cloneArray is " + (arrayOfInts == cloneArray));

        /*
            Printing out each structure shows that they both have elements w/ the same VALUES, but they do not
            have the same identities
         */
        System.out.print("Original Array: ");
        for (int arrayOfInt : arrayOfInts) {
            System.out.print(arrayOfInt + " ");
        }
        System.out.println();

        System.out.print("Cloned Array: ");
        for (int cloneInt : cloneArray) {
            System.out.print(cloneInt + " ");
        }
        System.out.println();

        /*
            Repeating the same for a multi-dimensional array results in a SHALLOW copy
         */
        int[][] anotherArrayOfInts = {{1, 2}, {3, 4}};
        int[][] shallowClone = anotherArrayOfInts.clone();

        // Still False.
        System.out.println("anotherArrayOfInts == shallowClone is " + (anotherArrayOfInts == shallowClone));

        // These are true, so the "first" dimension is a shallow copy.
        System.out.println("anotherArrayOfInts[0] == shallowClone[0] is " + (anotherArrayOfInts[0] == shallowClone[0]));
        System.out.println("anotherArrayOfInts[1] == shallowClone[1] is " + (anotherArrayOfInts[1] == shallowClone[1]));



    }
}
