package objects2.innerclass;


interface Teachable {
    void work();
}

public class Programmer {
    private String name;

    //Programmer�������������
    public Programmer() {
    }

    public Programmer(String name) {
        this.name = name;
    }

    //�˴�ʡ����name��setter��getter����
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void work() {
        System.out.println(name + "�ڵ��������ü���...");
    }
}