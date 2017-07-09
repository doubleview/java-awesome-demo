package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {

    void walk();

    void sayHello(String name);
}

class MyInvokationHandler implements InvocationHandler {
    /*
        ִ�ж�̬�����������з���ʱ�����ᱻ�滻��ִ�����µ�invoke����
        ���У�
        proxy������̬�������
        method����������ִ�еķ���
        args���������Ŀ�귽��ʱ�����ʵ�Ρ�
    */
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println(proxy.getClass().getName());
        System.out.println("----����ִ�еķ���:" + method);
        if (args != null) {
            System.out.println("������ִ�и÷���ʱ�����ʵ��Ϊ��");
            for (Object val : args) {
                System.out.println(val);
            }
        } else {
            System.out.println("���ø÷���û��ʵ�Σ�");
        }
        return null;
    }
}

public class ProxyTest {
    public static void main(String[] args) throws Exception {
        InvocationHandler handler = new MyInvokationHandler();

        // ʹ��ָ����InvocationHandler������һ����̬�������
        Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class}, handler);
        // ���ö�̬��������walk()��sayHello()����

        p.walk();
        p.sayHello("�����");
    }
}
