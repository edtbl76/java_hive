package BehavioralDesignPatterns.TemplateMethod.TemplateMethodDemo_1;

public class StoreOrder extends OrderTemplate {

    @Override
    public void doCheckout() {
        System.out.println("Ring up items from cart.");
    }

    @Override
    public void doPayment() {
        System.out.println("Process payment using Debit Card");
    }

    @Override
    public void doReceipt() {
        System.out.println("Email receipt to customer provided email.");
    }

    @Override
    public void doDelivery() {
        System.out.println("Bag items at counter.");
    }
}
