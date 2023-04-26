package CreationalDesignPatterns.Builder.Builder_3;

public class LunchOrder {

    /*
        Static inner class
     */
    public static class Builder {
        private String bread;
        private String condiments;
        private String dressing;
        private String meat;

        /*
            Whatever we add as parameters would enforce requirements as to what attributes
            are needed to fulfill the contract.
         */
        public Builder() { }

        public LunchOrder build() {
            return new LunchOrder(this);
        }


        public Builder withBread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder withCondiments(String condiments) {
            this.condiments = condiments;
            return this;
        }

        public Builder withDressing(String dressing) {
            this.dressing = dressing;
            return this;
        }

        public Builder withMeat(String meat) {
            this.meat = meat;
            return this;
        }

    }

    /*
        All of the attributes are FINAL (for immutability)
     */
    private final String bread;
    private final String condiments;
    private final String dressing;
    private final String meat;

    /*
        Static inner class above returns an instance of LunchOrder, which has chainable methods that allow
        for the flexibility of a Setter-based Java Bean.
     */
    private LunchOrder(Builder builder) {
        this.bread = builder.bread;
        this.condiments = builder.condiments;
        this.dressing = builder.dressing;
        this.meat = builder.meat;
    }

    /*
        No Setters give us the contract nature/immutability of a Telescoping Constructors
     */
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
