import java.util.HashMap;
import java.util.Map;

public class MapInterfaceDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Map map = new HashMap();

        map.put("Kam", "31");
        map.put("Earl", "29");
        map.put("Richard", "25");
        map.put("Bobby", "54");

        System.out.println("Map Elements: " + map);
    }
}
