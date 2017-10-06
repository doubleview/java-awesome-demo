package base;

import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * Created by doubleview on 2017/7/21.
 */
public class JoinerTest {

    public static void main(String args[]){
        JoinerTest joinerTest = new JoinerTest();
        joinerTest.testJoiner();
    }

    private void testJoiner(){
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1,2,3,4,5,null,6)));
    }

}