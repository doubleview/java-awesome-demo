package operate;

import rx.Observable;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午7:32
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class DoOnNextObservable {


    public static void main(String[] args) {
        Observable.just(1 , 2 ,  3 , 4)
            .doOnNext(integer -> System.out.println("doOnNext : " + integer)).subscribe(integer -> System.out.println("onNext : " + integer));
    }

}
