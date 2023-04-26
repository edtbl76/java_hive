package CreationalDesignPatterns.AbstractFactory.CreateFactory_1;

public class AmexFactory extends CreditCardFactory {

    /*
        Concrete Factory knows about ITS creation logic
     */
    @Override
    public CreditCard getCreditCard(CardType cardType) {
        // Instead of using NoArgs Constructor, we can use TemplateMethod Pattern.
        switch (cardType) {
            case GOLD:
                return new AmexGoldCreditCard();
            case PLATINUM:
                return new AmexPlatinumCreditCard();
            default:
                break;
        }
        return null;
    }

    @Override
    public Validator getValidator(CardType cardType) {
       switch (cardType) {
           case GOLD:
               return new AmexGoldValidator();
           case PLATINUM:
               return new AmexPlatinumValidator();
       }
       return null;
    }
}
