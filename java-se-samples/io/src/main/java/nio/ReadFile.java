package nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        try (
                // �����ļ�������
                FileInputStream fis = new FileInputStream("ReadFile.java");
                // ����һ��FileChannel
                FileChannel fcin = fis.getChannel()) {//�����ظ�ȡˮ
            // ����һ��ByteBuffer����
            ByteBuffer bbuff = ByteBuffer.allocate(256);
            // ��FileChannel�����ݷ���ByteBuffer��
            while (fcin.read(bbuff) != -1) {
                // ����Buffer�Ŀհ���
                bbuff.flip();
                // ����Charset����
                Charset charset = Charset.forName("GBK");
                // ����������(CharsetDecoder)����
                CharsetDecoder decoder = charset.newDecoder();
                // ��ByteBuffer������ת��
                CharBuffer cbuff = decoder.decode(bbuff);
                System.out.print(cbuff);
                // ��Buffer��ʼ����Ϊ��һ�ζ�ȡ������׼��
                bbuff.clear();
            }
        }
    }
}
