import java.util.*;

public class ListInterfaceClasses {

    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> vector = new Vector<>();
        List<Integer> stack = new Stack<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            arrayList.add(i);
            vector.add(i);
            stack.add(i);
            linkedList.add(i);
        }
        System.out.println("ArrayList : " + arrayList);
        System.out.println("Vector    : " + arrayList);
        System.out.println("Stack     : " + arrayList);
        System.out.println("LinkedList: " + arrayList);


        arrayList.remove(3);
        vector.remove(3);
        stack.remove(3);
        linkedList.remove(3);

        System.out.println("ArrayList : " + arrayList);
        System.out.println("Vector    : " + arrayList);
        System.out.println("Stack     : " + arrayList);
        System.out.println("LinkedList: " + arrayList);


        /*
            ForEach just for posterity.. I know it's slow.
         */
        arrayList.forEach(System.out::print);
        System.out.println();

        vector.forEach(System.out::print);
        System.out.println();

        stack.forEach(System.out::print);
        System.out.println();

        linkedList.forEach(System.out::print);
        System.out.println();
    }
}
