package objects2.finals;

import java.util.Arrays;

class Person1 {
    private int age;

    public Person1() {
    }

    // �в����Ĺ�����
    public Person1(int age) {
        this.age = age;
    }

    // ʡ��age��setter��getter����
    // age��setter��getter����
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }
}

public class FinalReferenceTest {
    public static void main(String[] args) {
        // final�������������iArr��һ�����ñ���
        final int[] iArr = {5, 6, 12, 9};
        System.out.println(Arrays.toString(iArr));
        // ������Ԫ�ؽ������򣬺Ϸ�
        Arrays.sort(iArr);
        System.out.println(Arrays.toString(iArr));
        // ������Ԫ�ظ�ֵ���Ϸ�
        iArr[2] = -8;
        System.out.println(Arrays.toString(iArr));
        // ��������iArr���¸�ֵ���Ƿ�
        // iArr = null;
        // final����Person������p��һ�����ñ���
        final Person1 p = new Person1(45);
        // �ı�Person�����ageʵ���������Ϸ�
        p.setAge(23);
        System.out.println(p.getAge());
        // ��������p���¸�ֵ���Ƿ�
        // p = null;
    }
}