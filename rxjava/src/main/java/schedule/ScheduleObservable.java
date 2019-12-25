package schedule;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午4:15
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class ScheduleObservable {

    public static void main(String[] args) throws InterruptedException {
        Observable.create((OnSubscribe<String>) subscriber -> {
            System.out.println("currentThreadName : " + Thread.currentThread().getName());
            subscriber.onNext("info1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("info2");
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    System.out.println("completed");
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("error");
                }

                @Override
                public void onNext(String s) {
                    System.out.println("currentThreadName-- : " + Thread.currentThread().getName());
                    System.out.println("onNext : " + s);
                }
            });


        Thread.sleep(10*1000);
    }
}
