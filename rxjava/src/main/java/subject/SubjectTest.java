package subject;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.subjects.AsyncSubject;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午7:05
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class SubjectTest {


    public static void main(String[] args) {
        AsyncSubject<String> publishSubject = AsyncSubject.create();
        Observable.create((OnSubscribe<String>) subscriber -> {
            subscriber.onNext("as bridge");
            subscriber.onCompleted();
        }).subscribe(publishSubject);


        publishSubject.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("subject : " + s);
            }
        });

    }
}
