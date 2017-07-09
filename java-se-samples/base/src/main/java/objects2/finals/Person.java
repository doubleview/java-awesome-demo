package objects2.finals;


class Name {
    private String firstName;
    private String lastName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // ʡ��firstName��lastName��setter��getter����
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }
}

public class Person {
    private final Name name;

    public Person(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public static void main(String[] args) {
        Name n = new Name("���", "��");
        Person p = new Person(n);
        // Person�����name��firstNameֵΪ"���"
        System.out.println(p.getName().getFirstName());
        // �ı�Person����name��firstNameֵ
        n.setFirstName("�˽�");
        // Person�����name��firstNameֵ����Ϊ"�˽�"
        System.out.println(p.getName().getFirstName());
    }
}
