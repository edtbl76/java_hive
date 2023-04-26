public class StringFormatting {

    public static void main(String[] args) {

        double floatVar = 1.1;
        int intVar = 11;
        String stringVar = "eleven";

        // using printf()
        System.out.printf("The value of the float variable is " +
                          "%f, while the value of the integer " +
                          "variable is %d, and the string " +
                          "is %s.\n",floatVar, intVar, stringVar);

        // using String.format()
        String fs;
        fs = String.format("The value of the float variable is " +
                        "%f, while the value of the integer " +
                        "variable is %d, and the string " +
                        "is %s.", floatVar, intVar, stringVar);
        System.out.println(fs);

    }
}
