package nio;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    public static void main(String[] args) throws Exception {

        try (
                // ʹ��FileOutputStream��ȡFileChannel
                FileChannel channel = new FileOutputStream("a.txt").getChannel()) {
            // ʹ�÷�����ʽ��ʽ��ָ���ļ�����
            FileLock lock = channel.tryLock();
            // ������ͣ10s
            Thread.sleep(10000);
            // �ͷ���
            lock.release();
        }
    }
}
