public class InstanceOf2 {

    public static void main(String[] args) {
        Vehicle v = new Car();
        boolean result = v instanceof Car;
        System.out.println( result );
    }
}


class Vehicle {}

class Car extends Vehicle {

}