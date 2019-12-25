package classloader;

import java.net.URL;

public class BootstrapTest {
    public static void main(String[] args) {
        // 鑾峰彇鏍圭被鍔犺浇鍣ㄦ墍鍔犺浇鐨勫叏閮║RL鏁扮粍
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        // 閬嶅巻銆佽緭鍑烘牴绫诲姞杞藉櫒鍔犺浇鐨勫叏閮║RL
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }
}

