package transform;

import rx.Observable;
import rx.Subscriber;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午3:28
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class ScanObservable {

    public static void main(String[] args) {
        Observable.just(1 , 2 , 3 , 4 , 5).scan((integer, integer2) -> integer+integer2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }

}
