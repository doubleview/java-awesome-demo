package customanotation.test2;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class ActionListenerInstaller {
    // ����Annotation�ķ���������obj�ǰ���Annotation�Ķ���
    public static void processAnnotations(Object obj) {
        try {
            // ��ȡobj�������
            Class<?> cl = obj.getClass();
            // ��ȡָ��obj��������г�Ա������������ÿ����Ա����
            for (Field f : cl.getDeclaredFields()) {
                // ���ó�Ա�������óɿ����ɷ��ʡ�
                f.setAccessible(true);
                // ��ȡ�ó�Ա������ActionListenerFor���͵�Annotation
                ActionListenerFor a = f.getAnnotation(ActionListenerFor.class);
                // ��ȡ��Ա����f��ֵ
                Object fObj = f.get(obj);
                // ���f��AbstractButton��ʵ������a��Ϊnull
                if (a != null && fObj != null && fObj instanceof AbstractButton) {
                    // ��ȡaע�����listnerԪ���ݣ�����һ���������ࣩ
                    Class<? extends ActionListener> listenerClazz = a.listener();
                    // ʹ�÷���������listner��Ķ���
                    ActionListener al = listenerClazz.newInstance();
                    AbstractButton ab = (AbstractButton) fObj;
                    // Ϊab��ť����¼�������
                    ab.addActionListener(al);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
