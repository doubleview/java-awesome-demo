package objects2.typehandle;


class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
}

public class PrintObject {
    public static void main(String[] args) {
        // ����һ��Person���󣬽�֮����p����
        Person p = new Person("�����");
        // ��ӡp�����õ�Person����
        System.out.println(p);
    }
}

