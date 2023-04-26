package CreationalPatterns.GoF.FactoryMethod.DeferredInstantiation2;

import CreationalPatterns.GoF.FactoryMethod.Models.*;

public class DeferredClassOneFactory extends DeferredParentFactory {

    /*
        This is the subclass of the Factory that decides what type of SimpleParent its going to create
     */

    @Override
    public SimpleParent create() {
        return new SimpleClassOne();
    }
}
