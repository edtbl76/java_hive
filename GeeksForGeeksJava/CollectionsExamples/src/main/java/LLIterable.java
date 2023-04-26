import java.util.Iterator;

/*
    Create a custom list in order to demonstrate Iterable<> interface
 */
class MyList<T> implements Iterable<T> {

    Node<T> head;
    Node<T> tail;

    public void add(T data) {
        Node<T> node = new Node<>(data, null);

        if (head == null) {
            tail = head = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public Node<T> getHead() {
        return head;
    }

    public Iterator<T> iterator() {
        return new MyListIterator<>(this);
    }
}

class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}


class MyListIterator<T> implements Iterator<T> {
    Node<T> current;

    public MyListIterator(MyList<T> myList) {
        current = myList.getHead();
    }

    @Override
    public boolean hasNext() {
        return current!= null;
    }

    public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}


public class LLIterable  {
    public static void main(String[] args) {

        MyList<String> myList = new MyList<>();
        myList.add("abc");
        myList.add("def");
        myList.add("ghi");
        myList.add("jkl");
        myList.add("mno");
        myList.add("pqr");
        myList.add("stu");
        myList.add("vw");
        myList.add("xyz");

        for (String string: myList) {
            System.out.println(string);
        }
    }

}
