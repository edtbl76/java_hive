package CollectionsInJava.PriorityQueue;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample2_Comparator {
    public static void main(String[] args) {
        Comparator<PQEmployee> nameSorter = Comparator.comparing(PQEmployee::getName);

        PriorityQueue<PQEmployee> priorityQueue = new PriorityQueue<>(nameSorter);

        priorityQueue.add(new PQEmployee(1, "AAA", LocalDate.now()));
        priorityQueue.add(new PQEmployee(4, "CCC", LocalDate.now()));
        priorityQueue.add(new PQEmployee(5, "BBB", LocalDate.now()));
        priorityQueue.add(new PQEmployee(2, "FFF", LocalDate.now()));
        priorityQueue.add(new PQEmployee(3, "DDD", LocalDate.now()));
        priorityQueue.add(new PQEmployee(6, "EEE", LocalDate.now()));

        while(true) {
            PQEmployee pq = priorityQueue.poll();
            System.out.println(pq);
            if(pq == null) break;
        }
    }
}
