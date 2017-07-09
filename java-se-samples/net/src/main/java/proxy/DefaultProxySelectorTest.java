package proxy;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Scanner;

public class DefaultProxySelectorTest {
    // ������Ҫ���ʵ���վ��ַ
    static String urlStr = "http://www.baidu.org";

    public static void main(String[] args) throws Exception {
        // ��ȡϵͳ��Ĭ������
        Properties props = System.getProperties();
        // ͨ��ϵͳ��������HTTP�������õĴ����������������ַ���˿�
        props.setProperty("http.proxyHost", "192.168.10.96");
        props.setProperty("http.proxyPort", "8080");
        // ͨ��ϵͳ��������HTTP��������ʹ�ô��������������
        // ����ʹ��*ͨ����������ַ��|�ָ�
        props.setProperty("http.nonProxyHosts", "localhost|192.168.10.*");
        // ͨ��ϵͳ��������HTTPS�������õĴ����������������ַ���˿�
        props.setProperty("https.proxyHost", "192.168.10.96");
        props.setProperty("https.proxyPort", "443");
        /* DefaultProxySelector��֧��https.nonProxyHosts���ԣ�
         DefaultProxySelectorֱ�Ӱ�http.nonProxyHosts�����ù����� */
        // ͨ��ϵͳ��������FTP�������õĴ����������������ַ���˿�
        props.setProperty("ftp.proxyHost", "192.168.10.96");
        props.setProperty("ftp.proxyPort", "2121");
        // ͨ��ϵͳ��������FTP��������ʹ�ô��������������
        props.setProperty("ftp.nonProxyHosts", "localhost|192.168.10.*");
        // ͨ��ϵͳ������������SOCKS�����������������ַ���˿�
        props.setProperty("socks.ProxyHost", "192.168.10.96");
        props.setProperty("socks.ProxyPort", "1080");
        // ��ȡϵͳĬ�ϵĴ���ѡ����
        ProxySelector selector = ProxySelector.getDefault();   // ��
        System.out.println("ϵͳĬ�ϵĴ���ѡ������" + selector);
        // ����URI��̬������ʹ�õĴ��������
        System.out.println("ϵͳΪftp://www.baidu.orgѡ��Ĵ��������Ϊ��"
                + ProxySelector.getDefault().select(new URI("ftp://www.baidu.org"))); // ��
        URL url = new URL(urlStr);
        // ֱ�Ӵ����ӣ�Ĭ�ϵĴ���ѡ������ʹ��http.proxyHost��
        // http.proxyPortϵͳ�������õĴ����������
        // ����޷����Ӵ����������Ĭ�ϵĴ���ѡ�����᳢��ֱ������
        URLConnection conn = url.openConnection();   // ��
        // ���ó�ʱʱ����
        conn.setConnectTimeout(3000);
        try (
                Scanner scan = new Scanner(conn.getInputStream(), "utf-8")) {
            // ��ȡԶ������������
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        }
    }
}
