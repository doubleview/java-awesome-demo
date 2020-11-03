package optimised;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    static AtomicReference<Integer> money = new AtomicReference<>();

    static {
        money.set(19);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    Integer m = money.get();
                    if (m < 20) {
                        if (money.compareAndSet(m, m + 20)) {
                            System.out.println("余额小于20元 , 充值成功，余额:" + money.get() + "元");
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }).start();
        }

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                while (true) {
                    Integer m = money.get();
                    if (m < 10) {
                        System.out.println("大于10元");
                        if (money.compareAndSet(m, m - 10)) {
                            System.out.println("成功消费10元，余额:" + money.get());
                            break;
                        }
                    } else {
                        System.out.println("没有足够的金额");
                        break;
                    }
                }
            }
        }).start();
    }
}
