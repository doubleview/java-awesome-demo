package enums.interface1;


public enum Gender implements GenderDesc {
    // �˴���ö��ֵ������ö�Ӧ������������
    MALE("��")
            // �����Ų���ʵ������һ�����岿��
            {
                public void info() {
                    System.out.println("���ö��ֵ��������");
                }
            },
    FEMALE("Ů") {
        public void info() {
            System.out.println("���ö��ֵ����Ů��");
        }
    };
    // ����������codes\06\6.9\best\Gender.java�е�Gender����ȫ��ͬ
    private final String name;

    // ö����Ĺ�����ֻ��ʹ��private����
    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // ���������info()������ʵ��GenderDesc�ӿڱ���ʵ�ֵķ���
    public void info() {
        System.out.println(
                "����һ���������ڶ����Ա��ö����");
    }
}
