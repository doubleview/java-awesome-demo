package objects2.java8interface;

public class CommandTest {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] target = {3, -4, 6, 4};
        // 绗竴娆″鐞嗘暟缁勶紝鍏蜂綋澶勭悊琛屼负鍙栧喅浜嶱rintCommand
        pa.process(target, new PrintCommand());
        System.out.println("------------------");
        // 绗簩娆″鐞嗘暟缁勶紝鍏蜂綋澶勭悊琛屼负鍙栧喅浜嶢ddCommand
        pa.process(target, new AddCommand());
    }
}
