import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetInterfaceDemo {

    public static void main(String[] args) {
        int[] count = {34, 22, 10, 60, 30, 22};
        Set<Integer> set = new HashSet<>();

        for (int num : count) {
            set.add(num);
        }

        TreeSet sortedSet = new TreeSet<>(set);
        System.out.println("The unsorted set is : " + set);
        System.out.println("The sorted set is : " + sortedSet);

        System.out.println("First Element: " + sortedSet.first());
        System.out.println("Last Element: " + sortedSet.last());


    }
}
