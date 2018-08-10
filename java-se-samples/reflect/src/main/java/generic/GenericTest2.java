package generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-03-27 下午6:35
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class GenericTest2 extends ArrayList<String>{

    public static void main(String[] args) {
        Type type = GenericTest2.class.getGenericSuperclass();
        System.out.println(type.getClass());
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            System.out.println(parameterizedType.getRawType().getTypeName());
            System.out.println(parameterizedType.getActualTypeArguments()[0].getTypeName());
        }
    }

}