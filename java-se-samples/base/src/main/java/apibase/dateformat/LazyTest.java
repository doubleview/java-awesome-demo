package apibase.dateformat;

import java.util.Calendar;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;

public class LazyTest {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(2003, 7, 31);  //2003-8-31
        // ���·���Ϊ9����9��31�ղ����ڡ�
        // c��ϵͳ�����cal�Զ�������10��1�ա�
        cal.set(MONTH, 8);
        // ����������10��1��
        System.out.println(cal.getTime());    //��
        // ����DATE�ֶ�Ϊ5
        cal.set(DATE, 5);    //��
        System.out.println(cal.getTime());    //��
    }
}
