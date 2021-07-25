package com.doubleview;

import com.doubleview.base.TreeNode;

/**
 * 二叉搜索书查找
 */
public class Solution_700 {


    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}
