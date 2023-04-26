import java.util.ArrayList;
import java.util.List;

public class PrimeDirective {


    public boolean isPrime(int number) {

        if (number == 2) {
            return true;
        } else if (number < 2) {
            return false;
        }

        for (int i = 2; i < number; i++) {

            if (number % i == 0) {
                return false;
            }

        }
        return true;
    }

    public List<Integer> onlyPrimes(int[] numbers) {
        List<Integer> primes = new ArrayList<>();

        for (int number : numbers) {
            if (isPrime(number)) {
                primes.add(number);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        PrimeDirective primeDirective = new PrimeDirective();
        int[] numbers = {6, 29, 28, 23, 11, 100, 101, 43, 89};

        // Tests
        System.out.println(primeDirective.isPrime(7));  // true
        System.out.println(primeDirective.isPrime(28)); // false
        System.out.println(primeDirective.isPrime(2));  // true
        System.out.println(primeDirective.isPrime(0));  // false

        System.out.println(primeDirective.onlyPrimes(numbers));

    }

}
