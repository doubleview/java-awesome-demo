package com.doubleview.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-12 上午10:00
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class RequestCacheCommand extends HystrixCommand<String> {

    private final int id;

    public RequestCacheCommand( int id) {
        super(HystrixCommandGroupKey.Factory.asKey("RequestCacheCommand"));
        this.id = id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName() + " execute id = " + id);
        return "execute=" + id;
    }

    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command1 = new RequestCacheCommand(2);
            RequestCacheCommand command2 = new RequestCacheCommand(2);
            System.out.println(command1.execute());
            System.out.println(command1.isResponseFromCache());

            System.out.println(command2.execute());
            System.out.println(command2.isResponseFromCache());
        }finally {
            context.shutdown();
        }

        context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command3 = new RequestCacheCommand(2);
            command3.execute();
            System.out.println(command3.isResponseFromCache());
        }finally {
            context.shutdown();
        }
    }
}
