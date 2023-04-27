package oop.abstraction.functionalinterfaces;

import utils.Generated;

@Generated
@FunctionalInterface
public interface Functional {

    void oneAbstractMethodAllowed();

    // This will cause a compiler error
    // void twoAbstractMethods();

    default void oneDefault() {
        // do nothing
    }

    default void twoDefault() {
        // do nothing
    }
}
