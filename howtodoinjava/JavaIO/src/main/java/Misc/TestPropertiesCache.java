package Misc;

public class TestPropertiesCache {

    public static void main(String[] args) {

        // Get Individual Propeprties
        System.out.println(PropertiesCache.getInstance().getProperty("firstName"));
        System.out.println(PropertiesCache.getInstance().getProperty("lastName"));

        System.out.println(PropertiesCache.getInstance().getAllPropertyNames());

        PropertiesCache cache = PropertiesCache.getInstance();
        if (!(cache.containsKey("country"))) {
            cache.setProperty("country", "USA");
        }

        if (!(cache.containsKey("state"))) {
            cache.setProperty("state", "MA");
        }

        if (!(cache.containsKey("town"))) {
            cache.setProperty("town", "Wilmington");
        }

        PropertiesCache.getInstance().getAllPropertyNames().forEach(
                name -> {
                    System.out.println(name + ": " + PropertiesCache.getInstance().getProperty(name));
                }
        );


    }
}
