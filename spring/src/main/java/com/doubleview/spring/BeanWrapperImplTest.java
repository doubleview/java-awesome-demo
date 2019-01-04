package com.doubleview.spring;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.testng.annotations.Test;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;


/**
 * HttpServletBean 125L
 * AbstractAutowireCapableBeanFactory 1134L
 */
public class BeanWrapperImplTest {

    @Test
    public void setPropertyValueTest() {
        User user = new User();
        user.setCard(new Card());

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(user);
        beanWrapper.setAutoGrowNestedPaths(true);

        beanWrapper.setPropertyValue("name", "胡成超");
        beanWrapper.setPropertyValue("age", 25);
        beanWrapper.setPropertyValue("hobbies[0]", "read");
        beanWrapper.setPropertyValue("infoMap[girlFriend]", "you");
        beanWrapper.setPropertyValue("card.code", "111");

        Map<String, Object> propertyMap = Maps.newHashMap();
        propertyMap.put("address", "51信用卡");
        beanWrapper.setPropertyValues(propertyMap);

        assertEquals(user.getName(), "胡成超");
        assertEquals(user.getAddress(), "51信用卡");
        assertEquals(user.getAge(), Integer.valueOf(25));
        assertThat(user.getHobbies(), hasItem("read"));
        assertEquals(user.getInfoMap().get("girlFriend"), "you");
        assertEquals(user.getCard().getCode(), Integer.valueOf(111));
    }

    @Test
    public void getPropertyValueTest() {
        User user = new User();
        user.setName("胡成超");
        user.setAge(25);

        Card card = new Card();
        card.setCode(111);
        card.setNumber("20190101");
        user.setCard(card);
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(user);

        assertEquals(beanWrapper.getPropertyType("name"), String.class);
        assertEquals(beanWrapper.getPropertyValue("name"), "胡成超");
        assertEquals(beanWrapper.getPropertyValue("age"), 25);
        assertEquals(beanWrapper.getPropertyValue("card.code"), 111);
    }

    @Test
    public void customConverterTest(){
        User user = new User();

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(user);
        beanWrapper.registerCustomEditor(Card.class, new CardPropertyEditor());
        beanWrapper.setPropertyValue("card", "111,20190101");

        assertEquals(user.getCard().getCode(), Integer.valueOf(111));
        assertEquals(user.getCard().getNumber(), "20190101");
    }

    @Data
    private class User {
        private String name;
        private Integer age;
        private String address;
        private List<String> hobbies;
        private Map<String, String> infoMap;

        private Card card;
    }

    @Data
    private class Card{
        private Integer code;
        private String number;
    }


    public  class CardPropertyEditor extends PropertyEditorSupport{

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            List<String> cardText = Splitter.on(",").splitToList(text);
            Card card = new Card();
            card.setCode(Integer.valueOf(cardText.get(0)));
            card.setNumber(cardText.get(1));
            setValue(card);
        }

        @Override
        public String getAsText() {
            Object value = getValue();
            if (value == null) {
                return "";
            }
            Card card = (Card)value;
            return card.getCode() + "," + card.getNumber();
        }
    }

}
