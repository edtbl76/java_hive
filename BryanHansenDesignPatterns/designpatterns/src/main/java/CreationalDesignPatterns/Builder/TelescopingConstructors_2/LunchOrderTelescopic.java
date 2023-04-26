package CreationalDesignPatterns.Builder.TelescopingConstructors_2;

public class LunchOrderTelescopic {


    private String bread;
    private String condiments;
    private String dressing;
    private String meat;

    /*
        PRO
            - this is immutable (no setters and private attrs)
        CON
            - this is actually less flexible than the BEAN, because the telescoping constructor only supports a few
            possible combinations. What if I want bread and meat only?

     */
    public LunchOrderTelescopic(String bread, String condiments) {
        this.bread = bread;
        this.condiments = condiments;
    }

    public LunchOrderTelescopic(String bread, String condiments, String dressing) {
        this(bread, condiments);
        this.dressing = dressing;
    }

    public LunchOrderTelescopic(String bread, String condiments, String dressing, String meat) {
        this(bread, condiments, dressing);
        this.meat = meat;
    }

    public String getBread() {
        return bread;
    }

    public String getCondiments() {
        return condiments;
    }

    public String getDressing() {
        return dressing;
    }

    public String getMeat() {
        return meat;
    }

}
