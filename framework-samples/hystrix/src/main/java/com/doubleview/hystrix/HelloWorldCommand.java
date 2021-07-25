package com.doubleview.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-10 上午9:55
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class HelloWorldCommand extends HystrixCommand<String> {

    private final String name;

    public HelloWorldCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        System.out.println("HelloWorldCommand run");
        return "Hello " + name + "thread" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("synchronous-hystrix");
        String result = helloWorldCommand.execute();
        System.out.println("result = " + result);

        helloWorldCommand = new HelloWorldCommand("asynchronous-hystrix");
        Future<String> future = helloWorldCommand.queue();
        result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("result = " + result);
        System.out.println("mainThread = " + Thread.currentThread().getName());
    }
}
