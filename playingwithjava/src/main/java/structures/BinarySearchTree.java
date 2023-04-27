package structures;

import lombok.Data;

@Data
public class BinarySearchTree {

    private BSTNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(int data) {
        this.root = new BSTNode(data);
    }

    public BinarySearchTree(BSTNode node) {
        this.root = node;
    }

    // Utilities
    public int min() {
        return min(this.root);
    }
    public int min(BSTNode node) {
        int minimum = node.getKey();
        BSTNode current = node;
        while (current.getLeft() != null) {
            minimum = current.getLeft().getKey();
            current = current.getLeft();
        }

        return minimum;
    }

    public boolean isEmpty() {
        return this.root == null;
    }


    @SuppressWarnings("UnusedReturnValue")
    // Insertion
    public boolean insert(int data) {

        this.root = insertRecursive(this.root, data);
        return true;
    }

    public BSTNode insertRecursive(BSTNode current, int data) {

        // base
        if (current == null) {
            return new BSTNode(data);
        }

        if (data < current.getKey()) {
            // Iterate left subtree
            current.setLeft(insertRecursive(current.getLeft(), data));
        } else if (data > current.getKey()) {
            current.setRight(insertRecursive(current.getRight(), data));
        } else {
            // already exists
            return current;
        }
        return current;
    }

    public void deleteKey(int key) {
        this.root = deleteRecursive(this.root, key);
    }

    public BSTNode deleteRecursive(BSTNode current, int data) {

        if (current == null) {
            return null;
        }

        // traverse
        if (data < current.getKey()) {
            // traverse left
            current.setLeft(deleteRecursive(current.getLeft(), data));
        } else if (data > current.getKey()) {
            // traverse right
            current.setRight(deleteRecursive(current.getRight(), data));
        } else {

            // node only has one child
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            }
                // node has two children
                // inorder successor
                current.setKey(min(current.getRight()));

                // Delete in order successor
                current.setRight(deleteRecursive(current.getRight(), current.getKey()));
        }
        return current;
    }

    public boolean search(int key) {
        BSTNode node = searchRecursive(this.root, key);
        return node != null;
    }

    public BSTNode searchRecursive(BSTNode node, int data) {
        // base: root is null or key is at root
        if (node == null || node.getKey() == data) {
            return node;
        }

        if (node.getKey() > data) return searchRecursive(node.getLeft(), data);
        return searchRecursive(node.getRight(), data);
    }

}
