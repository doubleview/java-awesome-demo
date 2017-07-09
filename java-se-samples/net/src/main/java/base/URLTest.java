package base;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class URLTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("ftp://www.baidu.com:8080/a/b/c?name=hcc");

        System.out.println("��Դ�� : " + url.getFile());
        System.out.println("������ : " + url.getHost());
        System.out.println("��Դ·�� : " + url.getFile());
        System.out.println("�˿� : " + url.getPort());
        System.out.println("Э�� : " + url.getProtocol());
        System.out.println("��ѯ�ַ��� : " + url.getQuery());

        System.out.println("----------------------------");
        url = new URL("http://www.baidu.com");
        // ��ȡ������Ӧͷ�ֶ�
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getContentEncoding());
        System.out.println(urlConnection.getContentLength());
        System.out.println(urlConnection.getContentType());
        System.out.println(urlConnection.getDate());
        System.out.println(urlConnection.getContent());
        Map<String, List<String>> map = urlConnection.getHeaderFields();
        // �������е���Ӧͷ�ֶ�
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }


    }
}
