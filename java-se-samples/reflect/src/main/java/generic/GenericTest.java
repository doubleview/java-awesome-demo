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

        Class<?> a = f.getType();// 直接使用getType()取出的类型只对普通类型的成员变量有效
        System.out.println("score的类型是:" + a);

        Type gType = f.getGenericType();// 获得成员变量f的泛型类型

        if (gType instanceof ParameterizedType) {

            ParameterizedType pType = (ParameterizedType) gType;
            Type rType = pType.getRawType();    // 获取原始类型
            System.out.println("原始类型是：" + rType.getTypeName());
            Type[] tArgs = pType.getActualTypeArguments(); // 取得泛型类型的泛型参数
            System.out.println("泛型信息是:");
            for (int i = 0; i < tArgs.length; i++) {
                System.out.println("第" + i + "个泛型类型是：" + tArgs[i].getTypeName());
            }

        } else {
            System.out.println("获取泛型类型出错！");
        }
    }
}
