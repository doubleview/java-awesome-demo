package base;

import com.google.common.base.CaseFormat;

/**
 * Created by doubleview on 2017/7/22.
 */
public class CaseFormatTest {

    public static void main(String args[]){
        CaseFormatTest test = new CaseFormatTest();
        test.testCaseFormat();
    }

    private void testCaseFormat(){
        String data = "test_data";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL ,  "test_data"));
    }
}
