import java.io.*;

public class DataInputStreamEx {

    public static void main(String[] args)
            throws IOException
    {

        // writing string to a file encoded as modified UTF-8
        DataOutputStream daos = new DataOutputStream(new FileOutputStream("file.txt"));
        daos.writeUTF("hello");

        // reading data from same file
        DataInputStream dais = new DataInputStream(new FileInputStream("file.txt"));

        while(dais.available()>0) {
            System.out.println("Available Bytes: " + dais.available());
            String k = dais.readUTF();
            System.out.print(k+" ");
        }
        System.out.println();
    }
}
