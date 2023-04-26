package CreationalDesignPatterns.Builder.Builder_3;

public class BuilderLunchOrderDemo {

    public static void main(String[] args) {
        LunchOrder.Builder builder = new LunchOrder.Builder();
        builder
                .withBread("Pumpernickel")
                .withCondiments("Swiss")
                .withDressing("Mustard")
                .withMeat("Chicken");
        LunchOrder lunchOrder = builder.build();

        System.out.println(String.join(", ",
                lunchOrder.getBread(), lunchOrder.getCondiments(), lunchOrder.getDressing(), lunchOrder.getMeat()));
    }
}
