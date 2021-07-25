package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMap {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/huchengchao/IdeaProjects/51NB/java-awesome-demo/java-se-samples/io/src/main/java/nio/a.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println(fileChannel.size());
        MappedByteBuffer mappedByteBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, randomAccessFile.length());
        System.out.println(mappedByteBuffer.remaining());
        mappedByteBuffer.put((byte) 'A');
        mappedByteBuffer.put((byte) 'B');
        mappedByteBuffer.put((byte) 'C');
        mappedByteBuffer.put((byte) 'D');
        mappedByteBuffer.force();
    }
}
