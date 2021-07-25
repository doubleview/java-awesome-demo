package com.doubleview.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import java.util.concurrent.TimeUnit;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-12 上午9:47
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class FallbackCommand extends HystrixCommand<String> {

    private String name;

    protected FallbackCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return "execute failed";
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("FallbackCommand run");
        return "Hello " + name + " thread: " + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        FallbackCommand command = new FallbackCommand("test-fallback");
        String result = command.execute();
        System.out.println("result = " + result);
    }
}
