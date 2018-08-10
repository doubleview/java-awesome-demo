package proxy.DynaProxy;


public class GunDog implements Dog {
    // 实现info()方法，仅仅打印一个字符串
    public void info() {
        System.out.println("我是一只猎狗");
    }

    // 实现run()方法，仅仅打印一个字符串
    public void run() {
        System.out.println("我奔跑迅速");
    }
}

