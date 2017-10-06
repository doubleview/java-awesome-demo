package observable;

import rx.Observable;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-20 下午2:06
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class EmptyObServable {

    public static void main(String[] args) {
        Observable.empty();
        Observable.never();
    }
}
