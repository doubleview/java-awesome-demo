package objects2.typehandle;


class Person1 {
    // ��дequals()�������ṩ�Զ������ȱ�׼
    public boolean equals(Object obj) {
        // �����жϣ����Ƿ���true����Person�������κζ������
        return true;
    }
}

// ����һ��Dog����
class Dog {
}

public class OverrideEqualsError {
    public static void main(String[] args) {
        Person1 p = new Person1();
        System.out.println("Person�����Ƿ�equals Dog����"
                + p.equals(new Dog()));
        System.out.println("Person�����Ƿ�equals String����"
                + p.equals(new String("Hello")));
    }
}

