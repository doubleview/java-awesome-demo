package queue;

import java.util.ArrayDeque;

public class ArrayDequeStack {
    public static void main(String[] args) {
        ArrayDeque stack = new ArrayDeque();
        // 依次将三个元素push入"栈"
        stack.push("Java开发");
        stack.push("轻量级Java EE企业应用实战");
        stack.push("Android开发");
        // 输出：[Android开发, 轻量级Java EE企业应用实战, Java开发]
        System.out.println(stack);
        // 访问第一个元素，但并不将其pop出"栈"，输出：Android开发
        System.out.println(stack.peek());
        // 依然输出：[Android开发, Java开发, 轻量级Java EE企业应用实战]
        System.out.println(stack);
        // pop出第一个元素，输出：Android开发
        System.out.println(stack.pop());
        // 输出：[轻量级Java EE企业应用实战, Java开发]
        System.out.println(stack);
    }
}
