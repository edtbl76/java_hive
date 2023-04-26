package StructuralDesignPatterns.Flyweight.FlyweightExample_1;

public class FlyweightInventoryDemo {

    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();

        inventorySystem.takeOrder("Roomba", 221);
        inventorySystem.takeOrder("Bose Headphones", 361);
        inventorySystem.takeOrder("Samsung TV", 432);
        inventorySystem.takeOrder("Samsung TV", 323);
        inventorySystem.takeOrder("Roomba", 563);
        inventorySystem.takeOrder("Bose Headphones", 321);
        inventorySystem.takeOrder("Roomba", 234);
        inventorySystem.takeOrder("Samsung TV", 54);
        inventorySystem.takeOrder("Roomba", 34);
        inventorySystem.takeOrder("Bose Headphones", 365);
        inventorySystem.takeOrder("Samsung TV", 332);
        inventorySystem.takeOrder("Roomba", 456);

        inventorySystem.process();
        System.out.println(inventorySystem.report());

        /*
            When you run this, you'll find that we only create 3 objects.
            1 each of the category above.
            Since these items are already registered by our catalog

            HUGE perf boost for a system.
            - SCALE BABY
         */
    }
}
