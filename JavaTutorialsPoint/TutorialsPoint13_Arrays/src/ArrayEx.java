public class ArrayEx {


    public static void main(String[] args) {
        // here's my Array1
        double[] myList = new double[10];

        // adding the values one at a time.
        myList[0] = 5.6;
        myList[1] = 4.5;
        myList[2] = 3.3;
        myList[3] = 13.2;
        myList[4] = 4.0;
        myList[5] = 34.33;
        myList[6] = 34.0;
        myList[7] = 45.45;
        myList[8] = 99.993;
        myList[9] = 11123;

        // printing them out for posterity.
        for( double d : myList) {
            System.out.println(d);
        }

    }
}
