package serialize.replace;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

public class Person
        implements Serializable {
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

    //	��дwriteReplace���������������л��ö���֮ǰ���ȵ��ø÷���
    private Object writeReplace() throws ObjectStreamException {
        ArrayList<Object> list = new ArrayList<>();
        list.add(name);
        list.add(age);
        return list;
    }
}