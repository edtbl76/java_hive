public class NullPatternDemo {

    public static void main(String[] args) {

        AbstractCustomer cust1 = CustomerFactory.getCustomer("Luke");
        AbstractCustomer cust2 = CustomerFactory.getCustomer("Jeff");
        AbstractCustomer cust3 = CustomerFactory.getCustomer("Jim");
        AbstractCustomer cust4 = CustomerFactory.getCustomer("Monte");

        System.out.println("Customers:");
        System.out.println(cust1.getName());
        System.out.println(cust2.getName());
        System.out.println(cust3.getName());
        System.out.println(cust4.getName());
    }
}
