package observable;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午2:17
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class IntervalObservable {

    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1 , TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("onNext:" + aLong);
            }
        });


        Thread.sleep(10*1000);
    }
}
