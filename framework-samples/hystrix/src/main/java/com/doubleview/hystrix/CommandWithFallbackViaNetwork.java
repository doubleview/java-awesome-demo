package com.doubleview.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-12 上午10:22
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class CommandWithFallbackViaNetwork extends HystrixCommand<String> {

    private final int id;

    protected CommandWithFallbackViaNetwork(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
        this.id = id;
    }

    @Override
    protected String run() {
        // RemoteService.getValue(id);
        System.out.println("CommandWithFallbackViaNetwork execute thread: " + Thread.currentThread().getName());
        throw new RuntimeException("force failure for example");
    }

    @Override
    protected String getFallback() {
        return new FallbackViaNetwork(id).execute();
    }

    public static void main(String[] args) {
        CommandWithFallbackViaNetwork commandWithFallbackViaNetwork = new CommandWithFallbackViaNetwork(1);
        commandWithFallbackViaNetwork.execute();
        System.out.println("MainThread: " + Thread.currentThread().getName());
    }

    private static class FallbackViaNetwork extends HystrixCommand<String> {

        private final int id;

        FallbackViaNetwork(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
                // 使用不同的线程池做隔离，防止上层线程池跑满，影响降级逻辑.
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
            this.id = id;
        }

        @Override
        protected String run() {
            System.out.println("FallbackViaNetNetWork execute thread" + Thread.currentThread().getName());
            return "FallbackViaNetNetWork";
        }

        @Override
        protected String getFallback() {
            return null;
        }
    }

}