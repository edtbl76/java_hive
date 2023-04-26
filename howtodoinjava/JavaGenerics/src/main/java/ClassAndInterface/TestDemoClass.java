package ClassAndInterface;

public class TestDemoClass {
    public static void main(String[] args) {

        BetterDemoClass<String> str = new BetterDemoClass<>();
        str.set("Edward");
        System.out.println(str.get());

        BetterDemoClass<Integer> num = new BetterDemoClass<>();
        num.set(2);
        System.out.println(num.get());

    }
}
