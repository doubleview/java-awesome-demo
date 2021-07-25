package com.doubleview.spring;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 *
 *  AbstractAutoProxyCreator 437L
 * 相当于 代理责任链
 */
public class ProxyFactoryTest {

    @Test
    public void test() {
        Service service = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(service);
        //使用cglib进行代理
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setPreFiltered(true);
        //在当前线程暴露出代理对象
        proxyFactory.setExposeProxy(true);

        //添加 Advisor
        proxyFactory.addAdvisor(new HelloAdvisor());
        //添加Advice
        proxyFactory.addAdvice(new AfterHelloAdvice());

        //冻结配置
        proxyFactory.setFrozen(true);
        Service proxy = (Service) proxyFactory.getProxy();
        proxy.hello();
    }


    private interface Service{
        void hello();
    }

    private class ServiceImpl implements Service {
        @Override
        public void hello() {
            Object proxy = AopContext.currentProxy();
            System.out.println("proxy class:" + proxy.getClass());
            System.out.println("hello world!");
        }
    }


    private class HelloAdvisor extends AbstractPointcutAdvisor{

        @Override
        public Pointcut getPointcut() {
            return new HelloPointcut();
        }

        @Override
        public Advice getAdvice() {
            return new BeforeHelloAdvice();
        }
    }

    private class HelloPointcut extends StaticMethodMatcherPointcut{

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return Service.class.isAssignableFrom(targetClass) && method.getName().equals("hello");
        }
    }

    private class BeforeHelloAdvice implements MethodInterceptor{

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Method method = invocation.getMethod();
            System.out.println("-------- before hello advice invoke method name ---------------: " + method.getName());
            return invocation.proceed();
        }
    }

    private class AfterHelloAdvice implements MethodInterceptor{

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {

            Object object =  invocation.proceed();
            Method method = invocation.getMethod();
            System.out.println("-------- after hello advice invoke method name ---------------: " + method.getName());
            return object;
        }
    }
}