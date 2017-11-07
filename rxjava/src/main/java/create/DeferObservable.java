package create;

import rx.Observable;
import rx.Subscriber;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-19 下午9:41
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class DeferObservable {

    public static void main(String[] args) {
        Observable<Integer> observable = Observable.defer(() -> Observable.just(1, 2, 3, 4));

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError :" + e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                System.out.println("onNext : " + o);
            }
        });

    }


}
