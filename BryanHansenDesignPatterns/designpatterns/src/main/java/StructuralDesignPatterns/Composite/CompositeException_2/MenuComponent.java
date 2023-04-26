package StructuralDesignPatterns.Composite.CompositeException_2;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuComponent {

    String name;
    String url;

    List<MenuComponent> menuComponents = new ArrayList<>();

    /*
        Establishes a contract in our ROOT class,
        for the type of operations we want to allow (or not
        allow)
     */
    public MenuComponent add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException("Feature Not Implemented At This Level");
    }

    public MenuComponent remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException("Feature Not Implemented At This Level");
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public abstract String toString();

    String print(MenuComponent menuComponent) {
        StringBuilder builder = new StringBuilder(name);
        builder
                .append(":")
                .append(url)
                .append("\n");
        return builder.toString();
    }
}
