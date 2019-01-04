package base;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;

public class UriTest {

    public static void main(String[] args) {
        URI uri = URI.create("https://web.u51.com/find-detail-new/index.html?feedId=feedType=胡成超");

        System.out.println("schema : " + uri.getScheme());
        System.out.println("schemaSpecificPart : " + uri.getSchemeSpecificPart());
        System.out.println("authority: " + uri.getAuthority());
        System.out.println("host : " + uri.getHost());
        System.out.println("port : " + uri.getPort());
        System.out.println("query : " + uri.getQuery());
        System.out.println("raw query : " + uri.getRawQuery());
        System.out.println("userInfo: " + uri.getUserInfo());
        System.out.println("path: " + uri.getPath());
        System.out.println("fragment: " + uri.getFragment());
        System.out.println(uri.toASCIIString());
        System.out.println(uri.toString());

        try {
            System.out.println(URLDecoder.decode(uri.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}