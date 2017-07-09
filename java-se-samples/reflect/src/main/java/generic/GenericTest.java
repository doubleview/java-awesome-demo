package generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTest {

    private Map<String, Integer> score;


    static {
        System.out.println("---------------");
    }


    public static void main(String[] args) throws Exception {


        System.out.println(Class.forName("java.lang.String").newInstance().getClass());

        Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");

        Class<?> a = f.getType();// ֱ��ʹ��getType()ȡ��������ֻ����ͨ���͵ĳ�Ա������Ч
        System.out.println("score��������:" + a);

        Type gType = f.getGenericType();// ��ó�Ա����f�ķ�������

        if (gType instanceof ParameterizedType) {

            ParameterizedType pType = (ParameterizedType) gType;
            Type rType = pType.getRawType();    // ��ȡԭʼ����
            System.out.println("ԭʼ�����ǣ�" + rType.getTypeName());
            Type[] tArgs = pType.getActualTypeArguments(); // ȡ�÷������͵ķ��Ͳ���
            System.out.println("������Ϣ��:");
            for (int i = 0; i < tArgs.length; i++) {
                System.out.println("��" + i + "�����������ǣ�" + tArgs[i].getTypeName());
            }

        } else {
            System.out.println("��ȡ�������ͳ���");
        }
    }
}
