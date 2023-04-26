public class Droid {

    private String name;
    private int batteryLevel;

    public Droid(String name) {
        this.name = name;
        this.batteryLevel = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void performTask(String task) {
        System.out.println(name + " is performing task: " + task);
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + name + '\'' +
                ", batteryLevel=" + batteryLevel +
                '}';
    }


    public static void main(String[] args) {
        Droid droid = new Droid("Baymax");
        System.out.println(droid);
        droid.performTask("fixing");
        droid.setBatteryLevel(10);
    }
}
