package create;

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
        Observable.just(1 , 2 , 3 , 4)
            .map(String::valueOf)
            .subscribeOn(Schedulers.io())
            .subscribe(System.out::println);

        Observable.just(1 , 2 , 3 , 4)
            .flatMap(Observable::just)
            .map(String::valueOf)
            .subscribeOn(Schedulers.io())
            .subscribe(System.out::println);
    }
}
