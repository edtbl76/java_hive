package BehavioralDesignPatterns.Visitor.WithVisitorDemo_2;

import java.util.List;

public class AtvPartsShippingVisitor implements AtvPartVisitor {

    // We can calculate shipping here without having to do it in each individual class.

    double shippingAmount = 0;

    @Override
    public void visit(Fender fender) {
        System.out.println("Fenders are light and cheap to ship.");
        shippingAmount += 9;
    }

    @Override
    public void visit(Oil oil) {
        System.out.println("Hazardous materials like oil have an added shipping fee.");
        shippingAmount += 3;
    }

    @Override
    public void visit(Wheel wheel) {
        System.out.println("Wheels are bulky and expensive to ship.");
        shippingAmount += 15;
    }

    @Override
    public void visit(PartsOrder partsOrder) {
        System.out.println("Discounts apply to orders of 4 items or more");

        List<AtvPart> parts = partsOrder.getParts();

        if (parts.size() > 3)
            shippingAmount -=5;

        System.out.println("Shipping: " + shippingAmount);

    }
}
