package GeneratingSerialVersionUID;

import java.io.*;
import java.util.logging.Logger;

public class DemoClass implements Serializable {

    private static final Long serialVersionUID = 4L;
    private static final String fileName = "DemoClassBytes.ser";
    private static final Logger logger = Logger.getLogger("");

    private static String staticVariable;
    private int intVariable;

    transient private String transientVariable = "This is a transient instance field";
    private Thread threadClass;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Serialize
        DemoClass test = new DemoClass();
        test.intVariable = 1;
        staticVariable = "this is a static variable";
        writeOut(test);
        System.out.println("DemoClass to be saved: " + test);

        // Deserialize
        System.out.println("DemoClass deserialized: " + readIn());
    }

    private static Object readIn() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)));
        return ois.readObject();
    }

    private static void writeOut(Serializable obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(obj);
        }
    }

    @Override
    public String toString() {
        return "DemoClass: final static fileName=" + fileName + ", final static logger=" +
                logger + ", non-final static staticVariable=" + staticVariable + ", instance intVariable=" +
                intVariable + ", transient instance transientVariable=" + transientVariable +
                ", non-serializable instance field threadClass:=" + threadClass;
    }
}
