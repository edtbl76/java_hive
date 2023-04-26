class CustomerFactory {

    private static final String[] names = {"Joe", "Jim", "Jeff"};

    public static AbstractCustomer getCustomer(String name){

        for (String n : names) {
            if(n.equalsIgnoreCase(name)) {
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}
