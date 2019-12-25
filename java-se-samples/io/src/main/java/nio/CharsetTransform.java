package nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetTransform {
    public static void main(String[] args) throws Exception {
        // 创建简体中文对应的Charset
        Charset cn = Charset.forName("gbk");
        // 获取cn对象对应的编码器和解码器
        CharsetEncoder cnEncoder = cn.newEncoder();
        CharsetDecoder cnDecoder = cn.newDecoder();
        // 创建一个CharBuffer对象
        CharBuffer cbuff = CharBuffer.allocate(8);
        cbuff.put('孙');
        cbuff.put('悟');
        cbuff.put('空');
        cbuff.flip();
        // 将CharBuffer中的字符序列转换成字节序列
        ByteBuffer bbuff = cnEncoder.encode(cbuff);
        System.out.println("cbuff的position: " + cbuff.position());
        System.out.println("cbuff的limit: " + cbuff.limit());
        System.out.println("cbuff的capacity: " + cbuff.capacity());

        System.out.println("bbuff的position: " + bbuff.position());
        System.out.println("bbuff的limit: " + bbuff.limit());
        System.out.println("bbuff的capacity: " + bbuff.capacity());
        // 循环访问ByteBuffer中的每个字节
        for (int i = 0; i < bbuff.capacity(); i++) {
            System.out.print(bbuff.get(i) + " ");
        }
        // 将ByteBuffer的数据解码成字符序列
        System.out.println("\n" + cnDecoder.decode(bbuff));
    }
}
