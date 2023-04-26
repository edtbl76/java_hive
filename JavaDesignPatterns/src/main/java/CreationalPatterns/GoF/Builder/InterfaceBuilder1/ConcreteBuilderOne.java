package CreationalPatterns.GoF.Builder.InterfaceBuilder1;

import CreationalPatterns.GoF.Builder.Model.*;

public class ConcreteBuilderOne implements InterfaceBuilder {

    private String name;
    private ComplexObject object;

    public ConcreteBuilderOne(String name) {
        object = new ComplexObject();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void start() {
        // Start with name
        object.add(String.format("Name[%s]", this.name));
    }

    @Override
    public void buildBase() {
        object.add("ConcreteBase");
    }

    @Override
    public void addWidget() {
        object.add("Widgetarian");
    }

    @Override
    public void insertDoohickey() {
        object.add("10 Doohickeys");
    }

    @Override
    public void end() {
        // empty
    }

    @Override
    public ComplexObject getComplexObject() {
        return object;
    }
}
