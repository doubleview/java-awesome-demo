package jackson.jsonfilter;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-03-13 обнГ4:00
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
@JsonFilter("myFilter")
public class MyDto {

    private int intValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
}