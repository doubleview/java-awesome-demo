package filter;

import rx.Observable;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午7:29
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class TakeObservable {

    public static void main(String[] args) {
        Observable.just(1 , 2 , 3 , 4 , 5).take(3).subscribe(System.out::println);
    }
}
