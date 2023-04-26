package CreationalDesignPatterns.Builder.TelescopingConstructors_2;

@SuppressWarnings("Duplicates")
public class LunchOrderBeanDemo {

    public static void main(String[] args) {

        LunchOrderTelescopic lunchOrderTelescopic =
                new LunchOrderTelescopic("Rye", "Mushrooms", "Pepper", "Steak");

        StringBuilder builder = new StringBuilder();
        builder.append(lunchOrderTelescopic.getBread()).append(", ");
        builder.append(lunchOrderTelescopic.getCondiments()).append(", ");
        builder.append(lunchOrderTelescopic.getDressing()).append(", ");
        builder.append(lunchOrderTelescopic.getMeat()).append(".");

        System.out.println(builder.toString());
    }

}
