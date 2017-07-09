package apibase.dateformat;

import java.util.Arrays;
import java.util.TimeZone;

public class TimeZoneTest {
    public static void main(String[] args) {
        // ȡ��Java��֧�ֵ�����ʱ��ID
        String[] ids = TimeZone.getAvailableIDs();
        System.out.println(Arrays.toString(ids));
        TimeZone my = TimeZone.getDefault();
        // ��ȡϵͳĬ��ʱ����ID:Asia/Shanghai
        System.out.println(my.getID());
        // ��ȡϵͳĬ��ʱ�������ƣ��й���׼ʱ��
        System.out.println(my.getDisplayName());
        // ��ȡָ��ID��ʱ�������ƣ�Ŧ������׼ʱ��
        System.out.println(TimeZone.getTimeZone("CNT").getDisplayName());
    }
}
