package apibase.locale;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {
    public static void main(String[] args) {
        // ��Ҫ����ʽ��������
        double db = 1234000.567;
        // �����ĸ�Locale���ֱ�����й����ձ����¹�������
        Locale[] locales = {Locale.CHINA, Locale.JAPAN
                , Locale.GERMAN, Locale.US};
        NumberFormat[] nf = new NumberFormat[12];
        // Ϊ�����ĸ�Locale����12��NumberFormat����
        // ÿ��Locale�ֱ���ͨ����ֵ��ʽ�����ٷֱȸ�ʽ�������Ҹ�ʽ��
        for (int i = 0; i < locales.length; i++) {
            nf[i * 3] = NumberFormat.getNumberInstance(locales[i]);
            nf[i * 3 + 1] = NumberFormat.getPercentInstance(locales[i]);
            nf[i * 3 + 2] = NumberFormat.getCurrencyInstance(locales[i]);
        }
        for (int i = 0; i < locales.length; i++) {
            String tip = i == 0 ? "----�й��ĸ�ʽ----" :
                    i == 1 ? "----�ձ��ĸ�ʽ----" :
                            i == 2 ? "----�¹��ĸ�ʽ----" : "----�����ĸ�ʽ----";
            System.out.println(tip);
            System.out.println("ͨ����ֵ��ʽ��"
                    + nf[i * 3].format(db));
            System.out.println("�ٷֱ���ֵ��ʽ��"
                    + nf[i * 3 + 1].format(db));
            System.out.println("������ֵ��ʽ��"
                    + nf[i * 3 + 2].format(db));
        }
    }
}

