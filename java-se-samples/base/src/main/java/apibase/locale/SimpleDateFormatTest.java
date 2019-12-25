package apibase.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {
    public static void main(String[] args)
            throws ParseException {
        Date d = new Date();
        // 创建一个SimpleDateFormat对象
        SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy年中第D天");
        // 将d格式化成日期，输出：公元2014年中第101天
        String dateStr = sdf1.format(d);
        System.out.println(dateStr);
        // 一个非常特殊的日期字符串
        String str = "14###三月##21";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y###MMM##d");
        // 将日期字符串解析成日期，输出：Fri Mar 21 00:00:00 CST 2014
        System.out.println(sdf2.parse(str));
    }
}
