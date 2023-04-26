package CollectionsInJava.PriorityBlockingQueue;

import CollectionsInJava.PriorityQueue.PQEmployee;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PBQExample2_Comparator {
    public static void main(String[] args) {
        Comparator<PQEmployee> nameSorter = Comparator.comparing(PQEmployee::getName);
        PriorityBlockingQueue<PQEmployee> pbq = new PriorityBlockingQueue<>(11, nameSorter);

        pbq.add(new PQEmployee(1, "AAA", LocalDate.now()));
        pbq.add(new PQEmployee(4, "CCC", LocalDate.now()));
        pbq.add(new PQEmployee(5, "BBB", LocalDate.now()));
        pbq.add(new PQEmployee(2, "FFF", LocalDate.now()));
        pbq.add(new PQEmployee(3, "DDD", LocalDate.now()));
        pbq.add(new PQEmployee(6, "EEE", LocalDate.now()));

        pbq.forEach(pqEmployee -> {
            System.out.println("Element: " + pbq.element());
            System.out.println("Still Here: " + pbq.poll());
            if (pqEmployee == null)
                System.out.println("Gone!");
            else
                System.out.println(pqEmployee);
        });
    }
}
