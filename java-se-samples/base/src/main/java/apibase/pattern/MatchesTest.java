package apibase.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchesTest {
    public static void main(String[] args) {
        String[] mails =
                {
                        "kongyeeku@163.com",
                        "kongyeeku@gmail.com",
                        "ligang@baidu.org",
                        "wawa@abc.xx"
                };
        String mailRegEx = "(\\w{3,20}@\\w+\\.(com|org|cn|net|gov))";
        Pattern mailPattern = Pattern.compile(mailRegEx);
        Matcher matcher = null;
        for (String mail : mails) {
            if (matcher == null) {
                matcher = mailPattern.matcher(mail);
            } else {
                matcher.reset(mail);
            }

            String result = mail + (matcher.matches() ? "是" : "不是")
                    + "一个有效的邮件地址！";
            System.out.println(result);
        }
    }
}

