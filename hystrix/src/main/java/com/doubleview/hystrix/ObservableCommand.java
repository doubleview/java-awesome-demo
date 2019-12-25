package com.doubleview.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-12 上午9:44
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class ObservableCommand extends HystrixCommand<String> {

    private String name;
    public ObservableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        System.out.println("HelloWorldCommand run");
        return "Hello " + name + "thread" + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        Observable<String> fs = new ObservableCommand("World").observe();
        fs.subscribe(s -> System.out.println("result = " + s));

        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("execute onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError " + throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });
    }
}
