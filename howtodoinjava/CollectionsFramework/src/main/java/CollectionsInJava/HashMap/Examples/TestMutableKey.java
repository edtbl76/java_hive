package CollectionsInJava.HashMap.Examples;

import java.util.HashMap;

public class TestMutableKey {

    public static void main(String[] args) {

        // Create HashMap w/ mutable Key
        HashMap<Account, String> map = new HashMap<>();

        // key 1
        Account a1 = new Account(1);
        a1.setHolderName("A_ONE");

        // key 2
        Account a2 = new Account(2);
        a2.setHolderName("A_TWO");

        // Put mutable Key and Value in map
        map.put(a1, a1.getHolderName());
        map.put(a2, a2.getHolderName());

        // Change key state (forcing hashmap to be recalculated)
        a1.setHolderName("Defaulter");
        a2.setHolderName("Bankrupt");

        // Success!! We're able to get back the values!
        System.out.println(map.get(a1) + "\n" + map.get(a2));

        // Create new key initialized w/ same account number (tests the equals() piece)
        Account a3 = new Account(1);
        a3.setHolderName("A_THREE");

        System.out.println(map.get(a3));
    }
}
