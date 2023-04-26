package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorSteps {

    private Calculator calc;

    /*
        ADDITION
     */
    @Given("^a calculator I just turned on$")
    public void setup() {
        calc = new Calculator();
    }

    @When("^I add (\\d+) and (\\d+)$")
    public void add(int argument1, int argument2) {
        calc.push(argument1);
        calc.push(argument2);
        calc.push("+");
    }

    /*
        SUBTRACTION
     */
    @When("^I subtract (\\d+) to (\\d+)")
    public void subtract(int argument1, int argument2) {
        calc.push(argument1);
        calc.push(argument2);
        calc.push("-");
    }



    @Then("^the result is (\\d+)$")
    public void resultOfAdd(double expected) {
        assertEquals(expected, calc.value());
    }



}
