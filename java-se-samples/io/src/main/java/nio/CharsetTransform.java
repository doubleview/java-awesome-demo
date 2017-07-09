package nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetTransform {
    public static void main(String[] args) throws Exception {
        // �����������Ķ�Ӧ��Charset
        Charset cn = Charset.forName("gbk");
        // ��ȡcn�����Ӧ�ı������ͽ�����
        CharsetEncoder cnEncoder = cn.newEncoder();
        CharsetDecoder cnDecoder = cn.newDecoder();
        // ����һ��CharBuffer����
        CharBuffer cbuff = CharBuffer.allocate(8);
        cbuff.put('��');
        cbuff.put('��');
        cbuff.put('��');
        cbuff.flip();
        // ��CharBuffer�е��ַ�����ת�����ֽ�����
        ByteBuffer bbuff = cnEncoder.encode(cbuff);
        System.out.println("cbuff��position: " + cbuff.position());
        System.out.println("cbuff��limit: " + cbuff.limit());
        System.out.println("cbuff��capacity: " + cbuff.capacity());

        System.out.println("bbuff��position: " + bbuff.position());
        System.out.println("bbuff��limit: " + bbuff.limit());
        System.out.println("bbuff��capacity: " + bbuff.capacity());
        // ѭ������ByteBuffer�е�ÿ���ֽ�
        for (int i = 0; i < bbuff.capacity(); i++) {
            System.out.print(bbuff.get(i) + " ");
        }
        // ��ByteBuffer�����ݽ�����ַ�����
        System.out.println("\n" + cnDecoder.decode(bbuff));
    }
}
