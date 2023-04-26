package CreationalDesignPatterns.AbstractFactory.CreateFactory_1;

public class AmexPlatinumValidator implements Validator {
    @Override
    public boolean isValid(CreditCard creditCard) {
        return false;
    }
}
