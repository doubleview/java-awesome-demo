package subject;

import rx.Subscriber;
import rx.subjects.BehaviorSubject;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午6:53
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class BehaviorSubjectTest {

    public static void main(String[] args) {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.onNext("next1");
        behaviorSubject.onNext("next2");
        behaviorSubject.onNext("next3");
        behaviorSubject.subscribe(new Subscriber<String>() {
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
                System.out.println(s);
            }
        });
        behaviorSubject.onNext("next4");
        behaviorSubject.onNext("next5");
        behaviorSubject.onNext("next6");
    }

}
