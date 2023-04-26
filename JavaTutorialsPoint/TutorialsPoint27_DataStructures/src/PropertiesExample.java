import java.util.*;

public class PropertiesExample {

    public static void main(String[] args) {
        Properties capitals = new Properties();
        Set states;
        String str;

        capitals.put("California", "Sacramento");
        capitals.put("Washington", "Olympia");
        capitals.put("Massachusetts", "Boston");
        capitals.put("Oregon", "Salem");
        capitals.put("Virgina", "Richmond");

        // Show states + capitals in hashtable
        states = capitals.keySet(); // get set-view of keys

        for (Object state : states) {
            str = (String) state;
            System.out.println("The capital of " + str + " is " +
                    capitals.getProperty(str) + ".");
        }
        System.out.println();

        // Look for state not in list -- specify default
        str = capitals.getProperty("Florida", "CrappyState");
        System.out.println("The capital of Florida is " + str + ".");

    }
}
