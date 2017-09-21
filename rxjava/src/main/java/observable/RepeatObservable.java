package observable;

import rx.Observable;
import rx.Subscriber;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午3:52
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class RepeatObservable {

    public static void main(String[] args) {
        Observable.just(1 , 2  , 3).repeat(3)
            .subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                    System.out.println("Sequence complete.");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("Error:" + e.getMessage());
                }

                @Override
                public void onNext(Integer integer) {
                    System.out.println("Next: " + integer);
                }
            });
    }
}
