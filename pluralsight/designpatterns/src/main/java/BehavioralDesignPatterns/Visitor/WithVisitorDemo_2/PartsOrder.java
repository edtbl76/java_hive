package BehavioralDesignPatterns.Visitor.WithVisitorDemo_2;

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


    @Override
    public void accept(AtvPartVisitor visitor) {
       parts.forEach(atvPart -> atvPart.accept(visitor));
       visitor.visit(this);
    }
}
