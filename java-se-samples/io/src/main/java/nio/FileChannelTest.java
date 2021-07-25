package nio;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
    public static void main(String[] args) {
        File f = new File("/Users/huchengchao/IdeaProjects/51NB/java-awesome-demo/java-se-samples/io/src/main/java/nio/FileChannelTest.java");
        try (
                // 创建FileInputStream，以该文件输入流创建FileChannel
                FileChannel inChannel = new FileInputStream(f).getChannel();
                // 以文件输出流创建FileBuffer，用以控制输出
                FileChannel outChannel = new FileOutputStream("/Users/huchengchao/IdeaProjects/51NB/java-awesome-demo/java-se-samples/io/src/main/java/nio/a.txt").getChannel()) {

//
            // 将FileChannel里的全部数据映射成ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel
                    .MapMode.READ_ONLY, 0, f.length());   // ①

            // 使用GBK的字符集来创建解码器
            Charset charset = Charset.forName("GBK");
            // 直接将buffer里的数据全部输出
            outChannel.write(buffer);     // ②
            // 再次调用buffer的clear()方法，复原limit、position的位置
            buffer.clear();
            // 创建解码器(CharsetDecoder)对象
            CharsetDecoder decoder = charset.newDecoder();
            // 使用解码器将ByteBuffer转换成CharBuffer
            CharBuffer charBuffer = decoder.decode(buffer);
            // CharBuffer的toString方法可以获取对应的字符串
            System.out.println(charBuffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
