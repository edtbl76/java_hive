package GenericTypeArrays;

public class ArrayStoreExceptionExample {

    public static void main(String[] args) {

        Object[] array = new String[10];
        array[0] = "Ed";
        array[1] = 10;  // this throws ArrayStoreException
    }
}
