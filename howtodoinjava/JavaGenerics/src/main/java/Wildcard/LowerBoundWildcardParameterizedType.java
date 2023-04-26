package Wildcard;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundWildcardParameterizedType {

    public static void main(String[] args) {

        // List of grand children
        List<GrandChildClass> grandChildren = new ArrayList<>();
        grandChildren.add(new GrandChildClass());
        addGrandChildren(grandChildren);

        // List of GrandChilds
        List<ChildClass> children = new ArrayList<>();
        children.add(new GrandChildClass());
        addGrandChildren(children);

        // List of Grand Supers
        List<SuperClass> supers = new ArrayList<>();
        supers.add(new GrandChildClass());
        addGrandChildren(supers);

    }

    public static void addGrandChildren(List<? super GrandChildClass> grandChildren) {
        grandChildren.add(new GrandChildClass());
        System.out.println(grandChildren);
    }
}

class SuperClass {}
class ChildClass extends SuperClass {}
class GrandChildClass extends ChildClass {}


