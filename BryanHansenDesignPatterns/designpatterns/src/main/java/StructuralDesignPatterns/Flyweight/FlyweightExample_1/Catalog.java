package StructuralDesignPatterns.Flyweight.FlyweightExample_1;

import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
public class Catalog {
    private Map<String, Item> items = new HashMap<>();


    // Factory Method
    public Item lookup(String itemName) {
        if (!items.containsKey(itemName))
            items.put(itemName, new Item(itemName));
        return items.get(itemName);
    }

    public int totalItemsMade() {
        return items.size();
    }
}
