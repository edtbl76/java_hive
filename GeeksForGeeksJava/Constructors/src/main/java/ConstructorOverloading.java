class Box {

    private final double width;
    private final double height;
    private final double depth;

    // Our Basic All Args Constructor
    Box (double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }


    // Empty box, i.e. a default constructor/No Args
    Box() {
        this.width = this.height = this.depth = 0;
    }

    /*
        Constructor that creates a cube
     */
    Box(double length) {
        this.width = this.height = this.depth = length;
    }

    public double getVolume() {
        return this.width * this.height * this.depth;
    }

}


public class ConstructorOverloading {

    public static void main(String[] args) {

        // Little Boxes...
        Box box1 = new Box(5, 10, 15);
        Box box2 = new Box();
        Box box3 = new Box(20);

        // ... on the hillside...
        System.out.println("Volume of box1: " + box1.getVolume());
        System.out.println("Volume of box2: " + box2.getVolume());
        System.out.println("Volume of box3: " + box3.getVolume());


    }

}
