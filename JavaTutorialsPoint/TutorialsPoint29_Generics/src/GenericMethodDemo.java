public class GenericMethodDemo {

    // generic method printArray
    private static < E > void printArray(E[] inputArray) {

        // display elems
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // create arrays of Integer, Double and Character
        Integer[] integers = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Character[] characters = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println("Array integers contains: ");
        printArray(integers);  // passing integer types

        System.out.println("\nArray doubles contains: ");
        printArray(doubles); // passing double types

        System.out.println("\nArray characters contains: ");
        printArray(characters); // passing character types
    }
}
