import java.util.*;

@SuppressWarnings("unchecked")
public class HashTableExample {

    public static void main(String[] args) {

        // create a hash map
        Hashtable balance = new Hashtable();
        Enumeration names;
        String str;
        double bal;

        balance.put("Zara", 3434.34);
        balance.put("Manny", 123.22);
        balance.put("Aidan", 1378.00);
        balance.put("Daisy", 99.22);
        balance.put("Quincy", -19.08);

        // show balances
        names = balance.keys();

        while(names.hasMoreElements()) {
            str = (String) names.nextElement();
            System.out.println(str + ": " + balance.get(str));
        }
        System.out.println();

        // Deposit moneyu
        bal = (Double) balance.get("Zara");
        balance.put("Zara", bal + 1000);
        System.out.println("Zara's new balance: " + balance.get("Zara"));

    }
}
