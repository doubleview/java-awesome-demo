package com.doubleview.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-12 上午9:44
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class ObservableCommand extends HystrixObservableCommand<String> {

    private String name;
    public ObservableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
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

    @Override
    protected Observable<String> construct() {
        return Observable.create((Observable.OnSubscribe<String>) observer -> {
            try {
                if (!observer.isUnsubscribed()) {
                    // a real example would do work like a network call here
                    observer.onNext("Hello");
                    observer.onNext(name + "!");
                    observer.onCompleted();
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        }).subscribeOn(Schedulers.io());
    }
}
