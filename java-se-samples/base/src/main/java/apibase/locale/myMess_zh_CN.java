package apibase.locale;

import java.util.ListResourceBundle;

public class myMess_zh_CN extends ListResourceBundle {
    // ������Դ
    private final Object myData[][] =
            {
                    {"msg", "{0}����ã������������{1}"}
            };

    // ��д����getContents()
    public Object[][] getContents() {
        // �÷���������Դ��key-value��
        return myData;
    }
}
