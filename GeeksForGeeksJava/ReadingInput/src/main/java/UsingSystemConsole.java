public class UsingSystemConsole {

    public static void main(String[] args) {

        try {
            String name = System.console().readLine();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}
