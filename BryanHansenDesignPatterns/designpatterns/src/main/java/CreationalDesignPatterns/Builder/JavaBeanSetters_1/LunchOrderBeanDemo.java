package CreationalDesignPatterns.Builder.JavaBeanSetters_1;

public class LunchOrderBeanDemo {

    public static void main(String[] args) {

        LunchOrderBean lunchOrderBean = new LunchOrderBean();
        lunchOrderBean.setBread("Wheat");
        lunchOrderBean.setCondiments("lettuce");
        lunchOrderBean.setDressing("mustard");
        lunchOrderBean.setMeat("turkey");

        StringBuilder builder = new StringBuilder();
        builder.append(lunchOrderBean.getBread()).append(", ");
        builder.append(lunchOrderBean.getCondiments()).append(", ");
        builder.append(lunchOrderBean.getDressing()).append(", ");
        builder.append(lunchOrderBean.getMeat()).append(".");

        System.out.println(builder.toString());
    }

}
