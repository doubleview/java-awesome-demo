package designmode.sorket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
public class MultiThreadEchoServer {

    private static ExecutorService tp = Executors.newCachedThreadPool();
    static class HandleMsg implements Runnable{

        Socket clientSocket;
        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;

            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String intputLine = null;
                long b = System.currentTimeMillis();
                while ((intputLine = is.readLine()) != null) {
                    os.println(intputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend : " + (e - b) + "ms");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (is != null) is.close();
                    if(os!=null) os.close();
                    if(clientSocket!=null) clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        try {
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
