package socket.Senior.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int SERVER_PORT = 30000;
    // ʹ��baiduMap����������ÿ���ͻ����ֺͶ�Ӧ�����֮��Ķ�Ӧ��ϵ��
    public static BaiduMap<String, PrintStream> clients = new BaiduMap<>();

    public void init() {
        try (// ����������ServerSocket
                ServerSocket ss = new ServerSocket(SERVER_PORT)) {
            // ������ѭ�������Ͻ������Կͻ��˵�����
            while (true) {
                Socket socket = ss.accept();
                new ServerThread(socket).start();
            }
        }
        // ����׳��쳣
        catch (IOException ex) {
            System.out.println("����������ʧ�ܣ��Ƿ�˿�"
                    + SERVER_PORT + "�ѱ�ռ�ã�");
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }
}

