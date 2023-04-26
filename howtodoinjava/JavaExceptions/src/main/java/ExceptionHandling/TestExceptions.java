package ExceptionHandling;

public class TestExceptions {

    public static void main(String[] args) {

        try {
            throw new DBException.NoData("No row found for id: x");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
