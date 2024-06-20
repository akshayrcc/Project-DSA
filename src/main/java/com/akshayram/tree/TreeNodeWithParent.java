package com.akshayram.tree;

public class TreeNodeWithParent {
    public int value;
    public boolean visited;
    public TreeNodeWithParent leftChild;
    public TreeNodeWithParent rightChild;
    public TreeNodeWithParent parent;

    public TreeNodeWithParent() {
    }

    public TreeNodeWithParent(int val) {
        this.value = val;
        this.visited = false;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

//    TreeNodeWithParent(String val) {
//        this.value = Integer.parseInt(val);
//        this.visited = false;
//        this.leftChild = null;
//        this.rightChild = null;
//        this.parent = null;
//    }

    TreeNodeWithParent(int val, TreeNodeWithParent left, TreeNodeWithParent right, TreeNodeWithParent parent) {
        this.value = val;
        this.leftChild = left;
        this.rightChild = right;
        this.parent = parent;
    }
}
