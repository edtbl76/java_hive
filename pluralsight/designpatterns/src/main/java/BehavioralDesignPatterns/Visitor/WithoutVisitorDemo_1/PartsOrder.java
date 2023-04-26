package BehavioralDesignPatterns.Visitor.WithoutVisitorDemo_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartsOrder implements AtvPart {

    private List<AtvPart> parts = new ArrayList<>();

    public PartsOrder() {
    }

    public void addPart(AtvPart atvPart) {
        parts.add(atvPart);
    }

    public List<AtvPart> getParts() {
        return Collections.unmodifiableList(parts);
    }


    /*
        Adding the atvPart.calculateShipping() method in order to calculate the total shipping
        requires that we change each one of our classes.
     */
    public double calculateShipping() {
        double shippingCost = 0;
        for (AtvPart atvPart: parts) {
            shippingCost += atvPart.calculateShipping();
        }
        return shippingCost;
    }
}
