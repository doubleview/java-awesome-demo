package jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-28 ����3:51
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
//�����л������δ֪���ԣ��Ƽ�����
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class User {

    private Date modifyTime;

    private String name;

    private Integer age;

    private BigDecimal height;

    //��ʽ�����������
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDay;

    private Date createTime;

    //��ʾ�����Խ������
    @JsonView
    private String internal = "";

    //ֱ����toString�ķ�ʽ���л�����ȥ��˫����""
    @JsonRawValue
    private Dog dog = new Dog();

    private String onlyField = "onlyField";




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    @JsonAnyGetter
    public Map<String , Integer> anyGetter() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        return map;
    }

    //�������ʽ����Ϊһ��get���������ص�ֵҲ�����л����
    @JsonGetter
    public Integer width() {
        return 10;
    }

    private class Dog{
        private String name = "blackTiger";
        private Integer age = 3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        }
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", height=" + height +
            ", birthDay=" + birthDay +
            ", createTime=" + createTime +
            ", modifyTime=" + modifyTime +
            '}';
    }
}
