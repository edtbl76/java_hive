public class UncheckedExceptionEx {

    public static void main(String[] args) {
        int[] num = {1,2,3,4};
        try {
            // deliberate Exception to demonstrate a Runtime Exception
            //noinspection ConstantConditions
            System.out.println(num[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
