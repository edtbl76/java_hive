package BehavioralDesignPatterns.Visitor.WithVisitorDemo_2;

public class Wheel implements AtvPart {

    @Override
    public void accept(AtvPartVisitor visitor) {
        visitor.visit(this);
    }
}
