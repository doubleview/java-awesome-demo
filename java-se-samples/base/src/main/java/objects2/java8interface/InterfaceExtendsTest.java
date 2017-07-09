package objects2.java8interface;


interface interfaceA {
    int PROP_A = 5;

    void testA();
}

interface interfaceB {
    int PROP_B = 6;

    void testB();
}

interface interfaceC extends interfaceA, interfaceB {
    int PROP_C = 7;

    void testC();
}

public class InterfaceExtendsTest {
    public static void main(String[] args) {
        System.out.println(interfaceC.PROP_A);
        System.out.println(interfaceC.PROP_B);
        System.out.println(interfaceC.PROP_C);
    }
}
