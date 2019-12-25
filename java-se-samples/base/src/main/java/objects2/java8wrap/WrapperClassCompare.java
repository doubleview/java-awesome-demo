package objects2.java8wrap;

public class WrapperClassCompare {
    public static void main(String[] args) {
        Integer a = new Integer(6);
        // 杈撳嚭true
        System.out.println("6鐨勫寘瑁呯被瀹炰緥鏄惁澶т簬5.0" + (a > 5.0));
        System.out.println("姣旇緝2涓寘瑁呯被鐨勫疄渚嬫槸鍚︾浉绛夛細" + (new Integer(2) == new Integer(2))); // 杈撳嚭false
        // 閫氳繃鑷姩瑁呯锛屽厑璁告妸鍩烘湰绫诲瀷鍊艰祴鍊肩粰鍖呰绫荤殑瀹炰緥
        Integer ina = 2;
        Integer inb = 2;
        System.out.println("涓や釜2鑷姩瑁呯鍚庢槸鍚︾浉绛夛細" + (ina == inb)); // 杈撳嚭true
        Integer biga = 128;
        Integer bigb = 128;
        System.out.println("涓や釜128鑷姩瑁呯鍚庢槸鍚︾浉绛夛細" + (biga == bigb)); // 杈撳嚭false
    }
}
