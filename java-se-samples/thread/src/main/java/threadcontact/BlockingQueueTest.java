package threadcontact;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args)
            throws Exception {
        // ����һ������Ϊ2����������
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.put("Java"); // ��bq.add("Java"��bq.offer("Java")��ͬ
        bq.put("Java"); // ��bq.add("Java"��bq.offer("Java")��ͬ
        bq.put("Java"); // �� �����̡߳�
    }
}
