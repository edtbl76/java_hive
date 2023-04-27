package structures;

import lombok.Data;

@Data
public class EMLinkedList {

    private LLNode head;

    // Constructors
    public EMLinkedList() {
        this.head = null;
    }

    public EMLinkedList(int value) {
        this.head = new LLNode(value);
    }

    public EMLinkedList(LLNode llNode) {
        this.head = llNode;
    }


    public EMLinkedList insert(LLNode llNode) {

       if (this.head == null) {
           this.head = llNode;
       } else {
           LLNode nextLLNode = this.head;
           while (nextLLNode.getNext() != null) {
               nextLLNode = nextLLNode.getNext();
           }
           nextLLNode.setNext(llNode);
       }

       return this;
    }

    public EMLinkedList insertAtHead(LLNode llNode) {

        if (this.head != null && this.head.getNext() != null) {
            llNode.setNext(this.head);
        }
        this.head = llNode;

        return this;
    }

    public EMLinkedList insert(int data) {
        return insert(new LLNode(data));
    }

    public EMLinkedList insertAtHead(int data) {
        return insertAtHead(new LLNode(data));
    }



    public boolean contains(int data) {
        return search(this.head, data);
    }

    private boolean search(LLNode llNode, int data) {

        // base
        if (llNode == null) {
            return false;
        }

        if (llNode.getValue() == data) {
            return true;
        } else {
            return search(llNode.getNext(), data);
        }
    }

    // Delete by value
    @SuppressWarnings("java:S2259")
    public EMLinkedList deleteByValue(int data) {

        /*
            Early exit. If head is null, just bail out.
         */
        if (this.head == null) return this;

        LLNode curr = this.head;
        LLNode prev = null;

        /*
            CASE 1:
            - data is at the head.
         */
        if (curr.getValue() == data) {
            this.head = curr.getNext();
            return this;
        }

        /*
            keep going until we either
                1.) hit the end of the list
                2.) find the value
         */
        while (curr != null && curr.getValue() != data) {
            prev = curr;
            curr = curr.getNext();
        }

        /*
            if curr isn't null, we found our data.
            we set prev's next to curr's next to skip current.
         */

        if (curr != null) {
            /*
                The IDE will complain about the possibility of an NPE here.
                (java:S2259)
                - prev is null if head is null, but we bail out early, no NPE
                - the while loop eliminates any further issues.
                TT -> sets prev to current, which prevents NPE
                TF -> the only possibility for TF is satisfied by the first case
                    (if the data is at the head)
                FX -> if the first part of the logic statement evals to false,
                    then head would have been null, which we bail out.
             */
            prev.setNext(curr.getNext());
        }

        return this;
    }

    // delete by index
    public EMLinkedList deleteByIndex(int index) {

        if (this.head == null) return this;

        LLNode curr = this.head;
        LLNode prev = null;

        // Head Case :)
        if (index == 0) {
            this.head = curr.getNext();
            return this;
        }

        // Everything else but tail
        int counter = 0;
        while (curr != null) {

            if (counter == index) {
                prev.setNext(curr.getNext());
                break;
            } else {
                prev = curr;
                curr = curr.getNext();
                counter++;
            }
        }
        return this;
    }
}
