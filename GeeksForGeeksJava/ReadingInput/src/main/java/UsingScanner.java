import java.util.Scanner;

public class UsingScanner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        System.out.println("You entered " + str);

        scanner.close();
    }
}
