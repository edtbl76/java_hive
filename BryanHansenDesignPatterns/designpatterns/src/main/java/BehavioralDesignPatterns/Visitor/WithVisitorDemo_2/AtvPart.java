package BehavioralDesignPatterns.Visitor.WithVisitorDemo_2;

// Element Interface
public interface AtvPart {
    void accept(AtvPartVisitor visitor);
}
