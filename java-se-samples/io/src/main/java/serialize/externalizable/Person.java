package serialize.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Person
        implements Externalizable {
    private String name;
    private int age;

    // ע��˴�û���ṩ�޲����Ĺ�����!
    public Person(String name, int age) {
        System.out.println("�в����Ĺ�����");
        this.name = name;
        this.age = age;
    }
    // ʡ��name��age��setter��getter����

    // name��setter��getter����
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // age��setter��getter����
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        // ��nameʵ��������ֵ��ת��д���������
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ����ȡ���ַ�����ת�󸳸�nameʵ������
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.age = in.readInt();
    }
}