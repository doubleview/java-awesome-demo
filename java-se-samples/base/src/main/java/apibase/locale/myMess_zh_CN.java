package apibase.locale;

import java.util.ListResourceBundle;

public class myMess_zh_CN extends ListResourceBundle {
    // 定义资源
    private final Object myData[][] =
            {
                    {"msg", "{0}，你好！今天的日期是{1}"}
            };

    // 重写方法getContents()
    public Object[][] getContents() {
        // 该方法返回资源的key-value对
        return myData;
    }
}
