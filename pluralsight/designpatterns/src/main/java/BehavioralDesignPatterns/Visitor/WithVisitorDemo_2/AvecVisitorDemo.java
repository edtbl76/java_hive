package BehavioralDesignPatterns.Visitor.WithVisitorDemo_2;


public class AvecVisitorDemo {

    public static void main(String[] args) {
        PartsOrder order = new PartsOrder();
        order.addPart(new Wheel());
        order.addPart(new Fender());
        order.addPart(new Oil());


        order.accept(new AtvPartsShippingVisitor());
    }
}
