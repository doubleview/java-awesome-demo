package queue;

import java.util.ArrayDeque;

public class ArrayDequeQueue {
    public static void main(String[] args) {
        ArrayDeque queue = new ArrayDeque();
        // 依次将三个元素加入队列
        queue.offer("Java开发");
        queue.offer("轻量级Java EE企业应用实战");
        queue.offer("Android开发");
        // 输出：[Java开发, 轻量级Java EE企业应用实战, Android开发]
        System.out.println(queue);
        // 访问队列头部的元素，但并不将其poll出队列"栈"，输出：Java开发
        System.out.println(queue.peek());
        // 依然输出：[Java开发, 轻量级Java EE企业应用实战, Android开发]
        System.out.println(queue);
        // poll出第一个元素，输出：Java开发
        System.out.println(queue.poll());
        // 输出：[轻量级Java EE企业应用实战, Android开发]
        System.out.println(queue);
    }
}
