package apibase.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {
    public static void main(String[] args)
            throws ParseException {
        Date d = new Date();
        // ����һ��SimpleDateFormat����
        SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy���е�D��");
        // ��d��ʽ�������ڣ��������Ԫ2014���е�101��
        String dateStr = sdf1.format(d);
        System.out.println(dateStr);
        // һ���ǳ�����������ַ���
        String str = "14###����##21";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y###MMM##d");
        // �������ַ������������ڣ������Fri Mar 21 00:00:00 CST 2014
        System.out.println(sdf2.parse(str));
    }
}
