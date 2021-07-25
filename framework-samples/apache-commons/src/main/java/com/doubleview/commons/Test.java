package com.doubleview.commons;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> map.compute("a", new BiFunction<String, Integer, Integer>() {
                @Override
                public Integer apply(String s, Integer integer) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("soa执行完成" + Thread.currentThread().getName());
                    return 1;
                }
            })).start();
        }
        Thread.sleep(100000000000L);
    }
}
