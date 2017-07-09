package nio;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTest {
    public static void main(String[] args) {
        // ��ȡJava֧�ֵ�ȫ���ַ���
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()) {
            // ����ַ����ı����Ͷ�Ӧ��Charset����
            System.out.println(alias + "----->" + map.get(alias));
        }
    }
}
