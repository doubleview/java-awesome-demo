package enums.best;


public enum Gender {
    // �˴���ö��ֵ������ö�Ӧ������������
    MALE("��"), FEMALE("Ů");
    private final String name;

    // ö����Ĺ�����ֻ��ʹ��private����
    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
