package BehavioralDesignPatterns.TemplateMethod.TemplateMethodDemo_1;

public abstract class OrderTemplate {

    public boolean isGift;

    public abstract void doCheckout();
    public abstract void doPayment();
    public abstract void doReceipt();
    public abstract void doDelivery();

    // final methods are part of the algorithm we DONT want the Concrete classes to change
    public final void wrapGift() {
        System.out.println("Gift was wrapped");
    }

    public final void processOrder() {
        doCheckout();
        doPayment();

        if (isGift)
            wrapGift();
        else
            doReceipt();

        doDelivery();
    }
}
