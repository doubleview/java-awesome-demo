package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ErrorUtils {
    @SafeVarargs
    public static void faultyMethod(List<String>... listStrArray) {
        // Java���Բ��������������飬���listArrayֻ�ܱ�����List[]����
        // ��ʱ�൱�ڰ�List<String>������List���Ѿ������ˡ�������
        List[] listArray = listStrArray;
        List<Integer> myList = new ArrayList<>();
        myList.add(new Random().nextInt(100));
        // ��listArray�ĵ�һ��Ԫ�ظ�ΪmyList
        listArray[0] = myList;
        Integer i = (Integer) listArray[0].get(0);
        System.out.println(i);
        String s = listStrArray[0].get(0);
    }
}