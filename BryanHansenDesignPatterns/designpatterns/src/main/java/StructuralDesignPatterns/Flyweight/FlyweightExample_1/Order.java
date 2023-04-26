package StructuralDesignPatterns.Flyweight.FlyweightExample_1;

public class Order {
    private final int orderNumber;
    private final Item item;

    /*
        Extrinsic state is passed in to an Order.
     */
    public Order(int orderNumber, Item item) {
        this.orderNumber = orderNumber;
        this.item = item;
    }

    void processOrder() {
        System.out.println("Ordering " + item + " for order number " + orderNumber);
    }
}
