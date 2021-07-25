package com.doubleview;

import com.doubleview.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度
 */
public class Solution_102 {

    /**
     * 深度优先遍历
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            return 1;
        }
        return 1 + Math.max(maxDepth(root.right), maxDepth(root.left));
    }

    /**
     * 广度优先遍历
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
