package nio;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    public static void main(String[] args) throws Exception {

        try (
                // 使用FileOutputStream获取FileChannel
                FileChannel channel = new FileOutputStream("a.txt").getChannel()) {
            // 使用非阻塞式方式对指定文件加锁
            FileLock lock = channel.tryLock();
            // 程序暂停10s
            Thread.sleep(10000);
            // 释放锁
            lock.release();
        }
    }
}
