package CollectionsInJava.PriorityQueue;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueExample1_NaturalOrdering {

    public static void main(String[] args) {
        PriorityQueue<PQEmployee> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new PQEmployee(1, "AAA", LocalDate.now()));
        priorityQueue.add(new PQEmployee(4, "CCC", LocalDate.now()));
        priorityQueue.add(new PQEmployee(5, "BBB", LocalDate.now()));
        priorityQueue.add(new PQEmployee(2, "FFF", LocalDate.now()));
        priorityQueue.add(new PQEmployee(3, "DDD", LocalDate.now()));
        priorityQueue.add(new PQEmployee(6, "EEE", LocalDate.now()));

        while(true) {
            PQEmployee pq = priorityQueue.poll();
            System.out.println(pq);
            if (pq == null)
                break;
        }
    }
}
