package filter;

import rx.Observable;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午7:27
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class FilterObservable {

    public static void main(String[] args) {
        Observable.just(1 , 2 , 3 , 4 , 5 , 6).filter(integer -> integer > 2).subscribe(
            integer -> System.out.println(integer));

    }
}
