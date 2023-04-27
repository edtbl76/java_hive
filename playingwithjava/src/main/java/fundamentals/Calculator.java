package fundamentals;

public class Calculator {

    private final double number1;
    private final double number2;
    public Calculator(double number1, double number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    double add() {
        return this.number1 + this.number2;
    }

    double subtract() {
        return this.number1 - this.number2;
    }

    double multiply() {
        return this.number1 * this.number2;
    }

    double divide() {
        return this.number1 / this.number2;
    }

}
