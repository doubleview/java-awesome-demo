package com.doubleview;

import com.doubleview.base.TreeNode;

import javax.management.QueryEval;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 */
public class Solution_104 {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(15);
        TreeNode n2 = new TreeNode(7);
        TreeNode n3 = new TreeNode(9);
        TreeNode n4 = new TreeNode(20, n1, n2);
        TreeNode n5 = new TreeNode(3, n3, n4);
        System.out.println(new Solution_104().levelOrder(n5));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> resultList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            resultList.add(levelList);
        }
        return resultList;
    }
}
