interface Vehicle {

    // all methods are abstract
    void changeGear(int a);
    void speedUp(int a);
    void applyBrakes(int a);
}

class Bicycle implements Vehicle {

    private int speed;
    private int gear;

    @Override
    public void changeGear(int gear) {
        this.gear = gear;
    }

    @Override
    public void speedUp(int speed) {
       this.speed += speed;
    }

    @Override
    public void applyBrakes(int speed) {
       this.speed -= speed;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "speed=" + speed +
                ", gear=" + gear +
                '}';
    }
}

class Bike implements Vehicle {

    private int speed;
    private int gear;

    @Override
    public void changeGear(int gear) {
        this.gear = gear;
    }

    @Override
    public void speedUp(int speed) {
        this.speed += speed;
    }

    @Override
    public void applyBrakes(int speed) {
        this.speed -= speed;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "speed=" + speed +
                ", gear=" + gear +
                '}';
    }
}


public class InterfaceExample {

    public static void main(String[] args) {

        Bicycle bicycle = new Bicycle();
        bicycle.changeGear(2);
        bicycle.speedUp(3);
        bicycle.applyBrakes(1);

        System.out.println("Bicycle State: " + bicycle);

        Bike bike = new Bike();
        bike.changeGear(1);
        bike.speedUp(4);
        bike.applyBrakes(3);

        System.out.println("Bike State: " + bike);

    }
}
