package serialize.custom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        // ��nameʵ��������ֵ��ת��д���������
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // ����ȡ���ַ�����ת�󸳸�nameʵ������
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.age = in.readInt();
    }
}