package base;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {
    public static void main(String[] args) throws Exception {
        // ��application/x-www-form-urlencoded�ַ���
        // ת������ͨ�ַ���
        // ���е��ַ���ֱ�Ӵ�ͼ17.3��ʾ���ڸ��ƹ���
        String keyWord = URLDecoder.decode("%E7%96%AF%E7%8B%82java", "utf-8");
        System.out.println(keyWord);
        // ����ͨ�ַ���ת����
        // application/x-www-form-urlencoded�ַ���
        String urlStr = URLEncoder.encode("Android����", "GBK");
        System.out.println(urlStr);
    }
}
