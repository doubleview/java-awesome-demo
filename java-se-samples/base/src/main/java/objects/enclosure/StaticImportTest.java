package objects.enclosure;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.System.out;

public class StaticImportTest {
    public static void main(String[] args) {
        // out��java.lang.System��ľ�̬��Ա�����������׼���
        // PI��java.lang.Math��ľ�̬��Ա��������ʾ�г���
        out.println(PI);
        // ֱ�ӵ���Math���sqrt��̬����
        out.println(sqrt(256));
    }
}
