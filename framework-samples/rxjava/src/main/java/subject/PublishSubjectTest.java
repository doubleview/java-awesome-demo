package subject;

import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午6:56
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class PublishSubjectTest {


    public static void main(String[] args) {
        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.onNext("next1");
        publishSubject.onNext("next2");
        publishSubject.onNext("next3");

        publishSubject.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error : " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });

        publishSubject.onNext("next4");
        publishSubject.onNext("next5");

    }

}
