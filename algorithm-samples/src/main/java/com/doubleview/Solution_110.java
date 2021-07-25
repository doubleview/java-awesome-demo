package com.doubleview;

import com.doubleview.base.TreeNode;

/**
 * 字符串反转
 */
public class Solution_110 {

    /**
     * 自顶向下
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.right) - height(root.left)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height2(root.left), height2(root.right)) + 1;
    }


    /**
     * 自底向上
     */
    public boolean isBalanced2(TreeNode root) {
        return height(root) >= 0;
    }

    public int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height2(root.left);
        int rightHeight = height2(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
