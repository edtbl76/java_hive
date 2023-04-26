package AdvancedTopics_4.AtomicVariablesAndNonblockingSynchronization_15.Examples;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue<E> {

    private final Node<E> dummy = new Node<>(null, null);

    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);

        while (true) {
            Node<E> currentTail = tail.get();
            Node<E> nextTail = currentTail.next.get();

            if (currentTail == tail.get()) {
                /*
                    Step A
                    We examine queue state, checking to see if it is INTERMEDIATE.
                 */
                if (nextTail != null) {
                    /*
                        Step B
                        Queue is in intermediate state.
                        - instead of waiting for it to finish, we'll advance the tail pointer for it
                          as the "fastest path" to ensuring that other threads can progress.

                        - when other threads
                     */
                    tail.compareAndSet(currentTail, nextTail);
                } else {
                    /*

                        Queue is in QUIESCENT state
                        - this means it's OUR turn!
                        - We can attempt to insert the new node.

                        Step C
                        The following if statement checks for success of insertion
                     */
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        /*
                            Step D
                            If the insertion was successful, we can advance the tail, completing the insertion
                         */
                        tail.compareAndSet(currentTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

    private static class Node<E> {

        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }
}
