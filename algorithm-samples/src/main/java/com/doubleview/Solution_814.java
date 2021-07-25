package com.doubleview;

import com.doubleview.base.TreeNode;

/**
 * 完全二叉树的节点个数
 */
public class Solution_814 {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }
}
