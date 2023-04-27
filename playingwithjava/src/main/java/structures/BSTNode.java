package structures;


import lombok.Data;

@Data
public class BSTNode {

    private int key;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }


}
