package jackson;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by doubleview on 2017/7/9.
 */
public class People {

    private String name;

    private  Integer age;

    private String photo;

    private String idCardNo;

    private Integer gender;

    private Date birthday;

    private Date createTime;

    private Date lastModifyTime;

    private String onlyField = "onlyField";

    private String onlyMethod;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Timestamp timestamp;

    private Double aDouble;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }


    public String getOnlyMethod() {
        return "onlyMethod";
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    @Override
    public String toString() {
        return "People{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", photo='" + photo + '\'' +
            ", idCardNo=" + idCardNo +
            ", gender=" + gender +
            ", birthday=" + birthday +
            ", createTime=" + createTime +
            ", lastModifyTime=" + lastModifyTime +
            ", onlyField='" + onlyField + '\'' +
            '}';
    }
}
