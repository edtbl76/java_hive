package structures;


import lombok.Data;

@Data
public class LLNode {

    private int value;
    private LLNode next;

    public LLNode(int value) {
        this.value = value;
        this.next = null;
    }


}
