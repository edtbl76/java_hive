package CreationalDesignPatterns.AbstractFactory.CreateFactory_1;

// Abstract Factory
public abstract class CreditCardFactory {

    /*
        This is just a collection of a factories.
        - common interface is passed back through all of our factories.

     */
    public static CreditCardFactory getCreditCardFactory(int creditScore) {

        if(creditScore > 650) {
            return new AmexFactory();
        }
        else {
            return new VisaFactory();
        }
    }

    public abstract CreditCard getCreditCard(CardType cardType);
    public abstract Validator getValidator(CardType cardType);
}
