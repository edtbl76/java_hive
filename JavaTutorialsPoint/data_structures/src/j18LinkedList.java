import java.util.Objects;

class j18LinkedList {

    static class Node {
        final int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node first = null;

    private void addAtHead(Node node) {
        node.next = first;
        first = node;
    }

    private void addAtTail(Node node) {
        if (Objects.isNull(first))
            first = node;
        else {
            Node ptr = first;
            while(Objects.nonNull(ptr.next))
                ptr = ptr.next;
            ptr.next = node;
        }
    }

    private void removeFront() {
        first = first.next;
    }

    private void output() {
        Node ptr = first;
        while(Objects.nonNull(ptr)) {
            System.out.print(ptr.value + " -> ");
            ptr = ptr.next;
        }
    }

    public static void main(String[] args) {
        j18LinkedList LL = new j18LinkedList();

        //no output
        LL.output();

        // Add Some Stuff and show output
        LL.addAtTail(new Node(5));
        LL.addAtHead(new Node(3));
        LL.addAtTail(new Node(4));
        LL.output();
        System.out.println();

        // Delete Stuff And Show Result
        LL.removeFront();
        LL.output();

    }
}


