package create;


import java.util.Arrays;
import rx.Observable;
import rx.Subscriber;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 上午10:38
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class FromObservable {

    public static void main(String[] args) {
        Observable.from(Arrays.asList(1, 2, 3)).subscribe(new Subscriber<Integer>() {
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


        Observable.from(Arrays.asList(1 , 2 , 3 , 4)).subscribe(integer -> System.out.println("onNext:"+integer),
            throwable -> System.out.println("error : " + throwable.getMessage()), () -> System.out.println("completed"));
    }


}
