package StructuralDesignPatterns.Flyweight.RW_Integer_0;

@SuppressWarnings("UnnecessaryBoxing")
public class EverydayDemo {

    public static void main(String[] args) {

        Integer firstInt = Integer.valueOf(5);
        Integer secondInt = Integer.valueOf(5);
        Integer thirdInt = Integer.valueOf(5);

        System.out.println(System.identityHashCode(firstInt));
        System.out.println(System.identityHashCode(secondInt));
        System.out.println(System.identityHashCode(thirdInt));
    }
}
