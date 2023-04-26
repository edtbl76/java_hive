package Intro_BestPractices;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;

public class TestUserDetails {

    public static void main(String[] args) {
        UserDetails myDetails = new UserDetails(
                "Ed", "Mangini", 22, LocalDate.of(2011, Month.NOVEMBER, 29));

        // Serialization Code
        try (FileOutputStream fos = new FileOutputStream("userDetails.ser");
             ObjectOutputStream out  = new ObjectOutputStream(fos)) {
            out.writeObject(myDetails);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Deserialization close
        try (FileInputStream fis = new FileInputStream("userDetails.ser");
             ObjectInputStream in = new ObjectInputStream(fis)) {

            UserDetails userDetails = (UserDetails) in.readObject();
            System.out.println(userDetails.getFirstName());
            System.out.println(userDetails.getLastName());
            System.out.println(userDetails.getAccountNumber());
            System.out.println(userDetails.getDateOpened());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
