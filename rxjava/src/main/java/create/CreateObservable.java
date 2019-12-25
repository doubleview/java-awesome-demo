package create;


import static java.lang.System.*;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-19 下午9:10
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class CreateObservable {

    public static void main(String[] args) {
        Observable.create((OnSubscribe<Integer>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 1; i < 5; i++) {
                        out.println("onNext:" + i);
                        subscriber.onNext(i);
                    }
                    out.println("onCompleted");
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                out.println("Sequence Completed");
            }

            @Override
            public void onError(Throwable e) {
                out.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                out.println("Next: " + integer);
            }
        });
    }

}