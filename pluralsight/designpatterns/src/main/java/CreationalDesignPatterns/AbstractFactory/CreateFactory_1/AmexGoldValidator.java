package CreationalDesignPatterns.AbstractFactory.CreateFactory_1;

public class AmexGoldValidator implements Validator {
    @Override
    public boolean isValid(CreditCard creditCard) {
        return false;
    }
}
