package proxy.DynaProxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {

    // Ϊָ��target���ɶ�̬�������
    public static Object getProxy(Object target) throws Exception {
        // ����һ��MyInvokationHandler����
        MyInvokationHandler handler = new MyInvokationHandler();
        // ΪMyInvokationHandler����target����
        handler.setTarget(target);
        // ������������һ����̬����
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader()
                , target.getClass().getInterfaces(), handler);
    }

}

