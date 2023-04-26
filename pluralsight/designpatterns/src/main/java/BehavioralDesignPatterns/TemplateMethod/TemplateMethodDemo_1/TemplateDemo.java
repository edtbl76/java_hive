package BehavioralDesignPatterns.TemplateMethod.TemplateMethodDemo_1;

public class TemplateDemo {

    public static void main(String[] args) {
        System.out.println(" --- Web Order --- ");

        OrderTemplate webOrder = new WebOrder();
        webOrder.processOrder();

        System.out.println("\n --- Store Order --- ");

        OrderTemplate storeOrder = new StoreOrder();
        storeOrder.processOrder();
    }
}
