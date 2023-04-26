package BehavioralDesignPatterns.Visitor.WithVisitorDemo_2;

public interface AtvPartVisitor {

    void visit(Fender fender);
    void visit(Oil oil);
    void visit(Wheel wheel);

    void visit(PartsOrder partsOrder);
}