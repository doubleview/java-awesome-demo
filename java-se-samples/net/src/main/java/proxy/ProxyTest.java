package proxy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class ProxyTest {
    // �����Ǵ���������ĵ�ַ�Ͷ˿ڣ�
    // ����ʵ����Ч�Ĵ���������ĵ�ַ�Ͷ˿�
    final String PROXY_ADDR = "129.82.12.188";
    final int PROXY_PORT = 3124;
    // ������Ҫ���ʵ���վ��ַ
    String urlStr = "http://www.baidu.com";

    public void init() throws IOException {
        URL url = new URL(urlStr);
        // ����һ���������������
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_ADDR, PROXY_PORT));
        // ʹ��ָ���Ĵ��������������
        URLConnection conn = url.openConnection(proxy);
        // ���ó�ʱʱ����
        conn.setConnectTimeout(5000);
        try (
                // ͨ�������������ȡ���ݵ�Scanner
                Scanner scan = new Scanner(conn.getInputStream(), "utf-8");
                PrintStream ps = new PrintStream("index.htm")) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                // �ڿ���̨�����ҳ��Դ����
                System.out.println(line);
                // ����ҳ��Դ���������ָ�������
                ps.println(line);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ProxyTest().init();
    }
}
