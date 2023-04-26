package Externalizable_BetterSerialization;

import java.io.*;

public class TestUserSettings {

    public static void main(String[] args) {
        UserSettings settings = new UserSettings();
        settings.setDontStoreMe("Sensitive Info");  // this won't get  stored.
        settings.setFieldOne(10);
        settings.setFieldTwo("Ten");
        settings.setFieldThree(true);

        // Before
        System.out.println(settings);
        storeUserSettings(settings);
        UserSettings loadedSettings = loadSettings();
        System.out.println(loadedSettings);
    }

    private static UserSettings loadSettings() {
        try (FileInputStream fis = new FileInputStream("object.ser");
             ObjectInputStream in = new ObjectInputStream(fis)) {
            return (UserSettings) in.readObject();
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static void storeUserSettings(UserSettings settings) {
        try (FileOutputStream fos = new FileOutputStream("object.ser");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(settings);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
