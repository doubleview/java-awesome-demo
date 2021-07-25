package com.doubleview.hystrix;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-08-12 上午10:28
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class CommandFacadeWithPrimarySecondary extends HystrixCommand<String> {

    private final static DynamicBooleanProperty usePrimary = DynamicPropertyFactory.getInstance()
        .getBooleanProperty("primarySecondary.usePrimary", true);
    private final int id;

    public CommandFacadeWithPrimarySecondary(int id) {
        super(Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("PrimarySecondaryCommand"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)));
        this.id = id;
    }

    @Override
    protected String run() {
        if (usePrimary.get()) {
            return new PrimaryCommand(id).execute();
        } else {
            return new SecondaryCommand(id).execute();
        }
    }

    @Override
    protected String getFallback() {
        return "static-fallback-" + id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }


    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            ConfigurationManager.getConfigInstance().setProperty("primarySecondary.usePrimary", true);
            System.out.println(new CommandFacadeWithPrimarySecondary(20).execute());
        } finally {
            context.shutdown();
            ConfigurationManager.getConfigInstance().clear();
        }


        context = HystrixRequestContext.initializeContext();
        try {
            ConfigurationManager.getConfigInstance().setProperty("primarySecondary.usePrimary", false);
            System.out.println(new CommandFacadeWithPrimarySecondary(20).execute());
        } finally {
            context.shutdown();
            ConfigurationManager.getConfigInstance().clear();
        }
    }

    private static class PrimaryCommand extends HystrixCommand<String> {

        private final int id;

        private PrimaryCommand(int id) {
            super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand"))
                .andCommandPropertiesDefaults(
                    // we default to a 600ms timeout for primary
                    HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(600)));
            this.id = id;
        }

        @Override
        protected String run() {
            // perform expensive 'primary' service call
            return "responseFromPrimary-" + id;
        }
    }

    private static class SecondaryCommand extends HystrixCommand<String> {

        private final int id;

        private SecondaryCommand(int id) {
            super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SecondaryCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SecondaryCommand"))
                .andCommandPropertiesDefaults(
                    // we default to a 100ms timeout for secondary
                    HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(100)));
            this.id = id;
        }

        @Override
        protected String run() {
            // perform fast 'secondary' service call
            return "responseFromSecondary-" + id;
        }
    }
}