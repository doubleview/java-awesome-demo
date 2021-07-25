package base;

import com.google.common.base.Splitter;

/**
 * Created by doubleview on 2017/7/22.
 */
public class SpliterTest {

    public static void main(String args[]){
        SpliterTest test = new SpliterTest();
        test.testSplitter();
    }

    private void testSplitter(){
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog."));
    }
}
