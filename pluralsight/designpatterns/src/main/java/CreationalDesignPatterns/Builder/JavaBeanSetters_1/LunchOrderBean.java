package CreationalDesignPatterns.Builder.JavaBeanSetters_1;

public class LunchOrderBean {

    /*
        Downside
        - setters make it not immutable.
        - method "contract" is unclear
            - what attributes are required to create a LunchOrder ?
            - i.e. what are constraints?
     */
    private String bread;
    private String condiments;
    private String dressing;
    private String meat;

    /*
        Traditional Bean Constructor
        - no arguments, and the method has only getters and setters.
        - This is flexible, because it allows all possible combinations.
     */
    public LunchOrderBean() {}

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

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setCondiments(String condiments) {
        this.condiments = condiments;
    }

    public void setDressing(String dressing) {
        this.dressing = dressing;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }
}
