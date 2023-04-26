package CreationalDesignPatterns.FactoryMethod.Enum_2;

import java.util.ArrayList;
import java.util.List;

public abstract class Website {

    protected List<Page> pages = new ArrayList<>();

    public Website() {
        this.createWebsite();
    }

    public List<Page> getPages() {
        return pages;
    }

    /*
        Important part of the factory method.
        - Concrete classes has to override this method.
     */
    public abstract void createWebsite();
}
