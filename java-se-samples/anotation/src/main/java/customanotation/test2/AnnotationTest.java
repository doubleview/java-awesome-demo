package customanotation.test2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnotationTest {
    private JFrame mainWin = new JFrame("ʹ��ע����¼�������");
    // ʹ��AnnotationΪok��ť���¼�������
    @ActionListenerFor(listener = OkListener.class)
    private JButton ok = new JButton("ȷ��");
    // ʹ��AnnotationΪcancel��ť���¼�������
    @ActionListenerFor(listener = CancelListener.class)
    private JButton cancel = new JButton("ȡ��");

    public void init() {
        // ��ʼ������ķ���
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);
        ActionListenerInstaller.processAnnotations(this);     // ��
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public static void main(String[] args) {
        new AnnotationTest().init();
    }
}

// ����ok��ť���¼�������ʵ����
class OkListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "������ȷ�ϰ�ť");
    }
}

// ����cancel��ť���¼�������ʵ����
class CancelListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "������ȡ����ť");
    }
}