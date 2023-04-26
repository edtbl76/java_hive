import java.util.*;

@SuppressWarnings({"unchecked", "WhileLoopReplaceableByForEach", "RedundantSuppression"})
public class IteratorListIteratorDemo {

    public static void main(String[] args) {

        ArrayList al = new ArrayList();

        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");

        // Use iterator to display contents
        // NOTE: this would be easier to do w/ a foreach/enhanced-for loop

        System.out.print("Original contents of al: ");
        Iterator itr = al.iterator();

        while(itr.hasNext()) {
            Object element = itr.next();
            System.out.print(element + " ");
        }
        System.out.println();

        // Use List Iterator so we can modify objects being iterator
        ListIterator litr = al.listIterator();

        while(litr.hasNext()) {
            Object element = litr.next();
            litr.set(element + "+");
        }
        System.out.print("Modified contents of al: ");
        itr = al.iterator();
        while(itr.hasNext()){
            Object element = itr.next();
            System.out.print(element + " ");
        }
        System.out.println();

        // Reverse the order
        System.out.print("Modified list backwards: ");

        while(litr.hasPrevious()){
            Object element = litr.previous();
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
