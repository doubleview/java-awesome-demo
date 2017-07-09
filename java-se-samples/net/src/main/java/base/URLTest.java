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

        System.out.println("资源名 : " + url.getFile());
        System.out.println("主机名 : " + url.getHost());
        System.out.println("资源路径 : " + url.getFile());
        System.out.println("端口 : " + url.getPort());
        System.out.println("协议 : " + url.getProtocol());
        System.out.println("查询字符串 : " + url.getQuery());

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
