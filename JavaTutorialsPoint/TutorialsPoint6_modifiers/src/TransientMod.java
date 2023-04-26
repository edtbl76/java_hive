import java.io.*;

@SuppressWarnings("SameParameterValue")
public class TransientMod implements Serializable {

    private final transient int limit;
    private final int b;

    private void printVals() {
        System.out.println("limit = " + this.limit);
        System.out.println("b = " + this.b);
    }

    private TransientMod(int limit, int b) {
        this.limit = limit;
        this.b = b;
    }

    @SuppressWarnings("UnusedAssignment")
    public static void main(String[] args) {
        TransientMod tm = new TransientMod(55, 9);
        String filename = "output.txt";

        try {
            // Serialization
            FileOutputStream fops = new FileOutputStream(filename);
            ObjectOutputStream oops = new ObjectOutputStream(fops);
            oops.writeObject(tm);
            oops.close();
            fops.close();

            System.out.println("Data before Serialization: ");
            tm.printVals();

            // reset





        } catch (IOException ex) {
            System.out.println("Oops");

        }

        //reset
        tm = null;

        try {

            // Deserialize
            FileInputStream fips = new FileInputStream(filename);
            ObjectInputStream oips = new ObjectInputStream(fips);

            tm = (TransientMod)oips.readObject();
            oips.close();
            fips.close();

            System.out.println("Data after Deserialization demonstrates transient variable! DOESNT PERSIST");
            tm.printVals();

        } catch (IOException ex) {
            System.out.println("Oops");
        } catch (ClassNotFoundException ex) {
            System.out.println("Oops2");
        }
    }
}
