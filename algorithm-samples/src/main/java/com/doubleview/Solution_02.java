package com.doubleview;

import com.doubleview.base.ListNode;

/**
 * 两数相加
 */
public class Solution_02 {

    public static void main(String[] args) {
        ListNode a = new ListNode(9);
        ListNode b = new ListNode(4, a);
        ListNode c = new ListNode(2, b);

        ListNode e = new ListNode(9);
        ListNode f = new ListNode(9, e);
        ListNode g = new ListNode(9, f);
        ListNode h = new ListNode(9, g);
        ListNode i = new ListNode(9, h);
        ListNode j = new ListNode(1, i);
        System.out.println(new Solution_02().addTwoNumbers(a, j));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode curr = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;

            carry = sum / 10;
            sum = sum % 10;
            curr.next = new ListNode(sum);
            curr = curr.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            curr.next = new ListNode(carry);
        }
        return pre.next;
    }
}
