package observable;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午3:05
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class MapObservable {

    public static void main(String[] args) {
        Observable.just(1 , 2 , 3 , 4).map(integer -> String.valueOf(integer)).subscribeOn(Schedulers.io())
            .subscribe(s -> System.out.println(s));


        Observable.just(1 , 2 , 3 , 4)
            .flatMap(integer -> Observable.just(integer)).map(integer -> String.valueOf(integer)).subscribeOn(Schedulers.io())
        .subscribe(s -> System.out.println(s));
    }
}
