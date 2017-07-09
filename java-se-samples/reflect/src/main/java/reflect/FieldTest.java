package reflect;

import java.lang.reflect.Field;

class Person {

    private String name;
    private int age;

    public String toString() {
        return "Person[name:" + name +
                " , age:" + age + " ]";
    }

}

public class FieldTest {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        Class<Person> personClazz = Person.class;

        // ��ȡPerson����Ϊname�ĳ�Ա����
        // ʹ��getDeclaredField()���������ɻ�ȡ���ַ��ʿ��Ʒ��ĳ�Ա����
        Field nameField = personClazz.getDeclaredField("name");

        // ����ͨ��������ʸó�Ա����ʱȡ������Ȩ�޼��
        nameField.setAccessible(true);
        // ����set()����Ϊp�����name��Ա��������ֵ
        nameField.set(p, "Yeeku.H.Lee");
        // ��ȡPerson����Ϊage�ĳ�Ա����
        Field ageField = personClazz.getDeclaredField("age");

        // ����ͨ��������ʸó�Ա����ʱȡ������Ȩ�޼��
        ageField.setAccessible(true);
        // ����setInt()����Ϊp�����age��Ա��������ֵ
        ageField.setInt(p, 30);
        System.out.println(p);
    }
}
