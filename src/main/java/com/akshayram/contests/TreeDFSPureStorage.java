package com.akshayram.contests;

import com.akshayram.tree.TreeNodeWithParent;

public class TreeDFSPureStorage {
    public static TreeNodeWithParent dfs(TreeNodeWithParent TreeNodeWithParent, int target) {
        System.out.print(TreeNodeWithParent.value + " ");
        TreeNodeWithParent.visited = true;

        if (TreeNodeWithParent.value == target) {
            return TreeNodeWithParent;
        }

        TreeNodeWithParent[] TreeNodeWithParents = {TreeNodeWithParent.rightChild, TreeNodeWithParent.parent, TreeNodeWithParent.leftChild};

        for (int i = 0; i < TreeNodeWithParents.length; ++i) {
            if (TreeNodeWithParents[i] != null && !TreeNodeWithParents[i].visited) {
                TreeNodeWithParent result = dfs(TreeNodeWithParents[i], target);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Example setup
        TreeNodeWithParent root = new TreeNodeWithParent(5);
        TreeNodeWithParent TreeNodeWithParent1 = new TreeNodeWithParent(1);
        TreeNodeWithParent TreeNodeWithParent4 = new TreeNodeWithParent(4);
        TreeNodeWithParent TreeNodeWithParent7 = new TreeNodeWithParent(7);
        TreeNodeWithParent TreeNodeWithParent3 = new TreeNodeWithParent(3);
        TreeNodeWithParent TreeNodeWithParent8 = new TreeNodeWithParent(8);
        TreeNodeWithParent TreeNodeWithParent9 = new TreeNodeWithParent(9);
        TreeNodeWithParent TreeNodeWithParent2 = new TreeNodeWithParent(2);
        TreeNodeWithParent TreeNodeWithParent11 = new TreeNodeWithParent(11);
        TreeNodeWithParent TreeNodeWithParent12 = new TreeNodeWithParent(12);
        TreeNodeWithParent TreeNodeWithParent5_1 = new TreeNodeWithParent(5);
        TreeNodeWithParent TreeNodeWithParent8_1 = new TreeNodeWithParent(8);
        TreeNodeWithParent TreeNodeWithParent1_1 = new TreeNodeWithParent(1);

        // Establish connections based on provided tree structure
        root.leftChild = TreeNodeWithParent4;
        root.rightChild = TreeNodeWithParent1;
        TreeNodeWithParent4.parent = root;
        TreeNodeWithParent1.parent = root;

        TreeNodeWithParent4.leftChild = TreeNodeWithParent7;
        TreeNodeWithParent4.rightChild = TreeNodeWithParent3;
        TreeNodeWithParent7.parent = TreeNodeWithParent4;
        TreeNodeWithParent3.parent = TreeNodeWithParent4;

        TreeNodeWithParent7.leftChild = TreeNodeWithParent8_1;
        TreeNodeWithParent7.rightChild = TreeNodeWithParent1_1;
        TreeNodeWithParent8_1.parent = TreeNodeWithParent7;
        TreeNodeWithParent1_1.parent = TreeNodeWithParent7;

        TreeNodeWithParent3.rightChild = TreeNodeWithParent9;
        TreeNodeWithParent9.parent = TreeNodeWithParent3;

        TreeNodeWithParent9.leftChild = TreeNodeWithParent2;
        TreeNodeWithParent2.parent = TreeNodeWithParent9;

        TreeNodeWithParent2.rightChild = TreeNodeWithParent12;
        TreeNodeWithParent12.parent = TreeNodeWithParent2;

        TreeNodeWithParent1.leftChild = TreeNodeWithParent8;
        TreeNodeWithParent8.parent = TreeNodeWithParent1;

        TreeNodeWithParent8.rightChild = TreeNodeWithParent5_1;
        TreeNodeWithParent5_1.parent = TreeNodeWithParent8;

        TreeNodeWithParent8.leftChild = TreeNodeWithParent11;
        TreeNodeWithParent11.parent = TreeNodeWithParent8;


        TreeNodeWithParent result = dfs(TreeNodeWithParent11, 12);
        System.out.println("\nResult Node Value: " + (result != null ? result.value : "null"));
    }
}
