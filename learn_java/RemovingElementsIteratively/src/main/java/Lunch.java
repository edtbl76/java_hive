import java.util.ArrayList;
import java.util.List;

public class Lunch {


    public static void removeAnts(List<String> lunchBox) {

        for (int i = 0; i < lunchBox.size(); i++) {
            if (lunchBox.get(i).equals("ant")) {
                lunchBox.remove(i);
                i--;
            }
        }

    }

    public static void main(String[] args) {
        List<String> lunchContainer = new ArrayList<>(List.of("apple", "ant", "ant", "sandwich", "ant"));
        removeAnts(lunchContainer);
        System.out.println(lunchContainer);

    }

}
