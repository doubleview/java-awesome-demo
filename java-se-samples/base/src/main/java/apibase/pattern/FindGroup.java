package apibase.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindGroup {
    public static void main(String[] args) {
        // ʹ���ַ���ģ��������ϵõ�����ҳԴ��
        String str = "������һ����Java��������������ϵ��13500006666"
                + "�����ѣ��绰������13611125565"
                + "���۶��ֵ��ԣ���ϵ��ʽ13899903312";
        // ����һ��Pattern���󣬲���������һ��Matcher����
        // ��������ʽֻץȡ13X��15X�ε��ֻ��ţ�
        // ʵ��Ҫץȡ��Щ�绰���룬ֻҪ�޸�������ʽ���ɡ�
        Matcher m = Pattern.compile("((13\\d)(\\d{8}))")
                .matcher(str);
        System.out.println(m.groupCount());
        // �����з���������ʽ���Ӵ����绰���룩ȫ�����
        while (m.find()) {
            System.out.println(m.group());
            System.out.println("����");
            for (int i = 0; i < m.groupCount() + 1; i++) {
                System.out.println(m.group(i));
            }
            System.out.println("------------");
        }

    }
}

