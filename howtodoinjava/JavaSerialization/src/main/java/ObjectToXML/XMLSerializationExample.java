package ObjectToXML;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLSerializationExample {

    public static void main(String[] args) throws IOException  {
        UserSettings settings = new UserSettings();
        settings.setFieldOne(10);
        settings.setFieldTwo("ten");
        settings.setFieldThree(true);

        // Before
        System.out.println(settings);
        serializeToXML(settings);
        UserSettings loadedSettings = deserializeFromXML();
        // After
        System.out.println(loadedSettings);
    }

    private static void serializeToXML(UserSettings settings) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("userSettings.xml");
             XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception: " + e.toString());
                }
            });
            encoder.writeObject(settings);
        }
    }

    private static UserSettings deserializeFromXML() throws IOException {

        try (FileInputStream fis = new FileInputStream("userSettings.xml");
             XMLDecoder decoder = new XMLDecoder(fis)) {
            return (UserSettings) decoder.readObject();
        }
    }
}
