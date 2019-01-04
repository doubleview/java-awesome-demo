package com.doubleview.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-12 上午10:18
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class SemaphoreCommand extends HystrixCommand<String> {

    private final String name;

    public SemaphoreCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "HystrixThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        SemaphoreCommand command = new SemaphoreCommand("semaphore");
        String result = command.execute();
        System.out.println(result);
        System.out.println("MainThread:" + Thread.currentThread().getName());
    }
}
