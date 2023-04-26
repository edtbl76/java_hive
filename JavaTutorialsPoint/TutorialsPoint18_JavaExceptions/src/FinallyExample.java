public class FinallyExample {

    public static void main(String[] args) {
        int[] a = new int[2];

        try{
            // deliberate bad code! We are demonstrating the use of an exception + finally block.
            //noinspection ConstantConditions
            System.out.println("Access Element three :" + a[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            a[0] = 6;
            System.out.println("First element value " + a[0]);
            System.out.println("Finally has left the building!");
        }
    }

}
