package jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by doubleview on 2017/7/9.
 */
public class People {

    private String name;

    private  int age;

    private String www = "www";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonIgnore
    public String getNamee() {
        return name;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
