package apibase.dateformat;

import java.util.Calendar;

import static java.util.Calendar.MONTH;

public class LenientTest {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        // �����YEAR�ֶμ�1��MONTH�ֶ�Ϊ1�����£�
        cal.set(MONTH, 13);   //��
        System.out.println(cal.getTime());
        // �ر��ݴ���
        cal.setLenient(false);
        // ��������ʱ�쳣
        cal.set(MONTH, 13);   //��
        System.out.println(cal.getTime());
    }
}
