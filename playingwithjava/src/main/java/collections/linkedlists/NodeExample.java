package collections.linkedlists;

import utils.Generated;

@Generated
public class NodeExample {

    private NodeExample() {}

    @Generated
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

}


