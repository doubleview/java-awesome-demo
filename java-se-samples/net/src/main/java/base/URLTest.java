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
        URL url = new URL("ftp://www.baidu.com:8080/a/b/c?name=胡成超");

        System.out.println("file : " + url.getFile());
        System.out.println("host : " + url.getHost());
        System.out.println("port : " + url.getPort());
        System.out.println("protocol : " + url.getProtocol());
        System.out.println("query : " + url.getQuery());
        System.out.println("authority: " + url.getAuthority());
        System.out.println("userInfo: " + url.getUserInfo());
        System.out.println("ref: " + url.getRef());
        System.out.println("path: " + url.getPath());

        System.out.println("----------------------------");
        url = new URL("http://www.baidu.com");
        // 获取所有响应头字段
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getContentEncoding());
        System.out.println(urlConnection.getContentLength());
        System.out.println(urlConnection.getContentType());
        System.out.println(urlConnection.getDate());
        System.out.println(urlConnection.getContent());
        Map<String, List<String>> map = urlConnection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
    }
}