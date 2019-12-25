package subject;

import rx.Observer;
import rx.subjects.AsyncSubject;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午6:48
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class AsyncSubjectTest {


    public static void main(String[] args) {
        AsyncSubject<String> asyncSubject= AsyncSubject.create();
        asyncSubject.onNext("next1");
        asyncSubject.onNext("next2");
        asyncSubject.onNext("next3");
        asyncSubject.subscribe(new Observer<String>() {
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
        asyncSubject.onNext("next4");
        asyncSubject.onCompleted();
    }


}