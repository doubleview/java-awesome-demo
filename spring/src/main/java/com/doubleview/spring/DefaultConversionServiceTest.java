package com.doubleview.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class DefaultConversionServiceTest {

    @Test
    public void conversionServiceTest() {
        //基本类型转换
        ConversionService conversionService = DefaultConversionService.getSharedInstance();
        assertEquals(conversionService.convert("FALSE", Boolean.class), Boolean.FALSE);
        assertEquals(conversionService.convert("123", Integer.class), Integer.valueOf(123));

        //自定义转换器
        ((DefaultConversionService) conversionService).addConverter(new PatternConverter());
        assertTrue(conversionService.canConvert(String.class, Pattern.class));
        Pattern pattern = conversionService.convert("\\w", Pattern.class);
        assertNotNull(pattern);

        //通过工厂方法转换对象
        assertTrue(conversionService.canConvert(Person.class, Animal.class));
        assertEquals(conversionService.convert(new Person("胡成超"), Animal.class).getName(), "胡成超");
    }


    @Test
    public void formatConversionServiceTest() {
        DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();
//        defaultFormattingConversionService.addFormatter(new Formatter<Person>() {
//            @Override
//            public Person parse(String text, Locale locale) {
//                return new Person(text);
//            }
//
//            @Override
//            public String print(Person object, Locale locale) {
//                return object.getName();
//            }
//        });

        String personString = defaultFormattingConversionService.convert(new Person("胡成超"), String.class);
        assertEquals(personString, "胡成超");
        Person person = defaultFormattingConversionService.convert("胡成超", Person.class);
        assertNotNull(person);
    }



    @Test
    public void useConversionServiceInBeanWrapper() {
        User user = new User();

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(user);
        ConversionService conversionService = new DefaultFormattingConversionService();
        beanWrapper.setConversionService(conversionService);

        beanWrapper.setPropertyValue("animal", new Person());
        beanWrapper.setPropertyValue("person", "胡成超");
        assertNotNull(user.getAnimal());
    }


    private class PatternConverter implements Converter<String, Pattern> {
        @Override
        public Pattern convert(String source) {
            return Pattern.compile(source);
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Person{
        private String name;

        public Animal toAnimal(){
            return new Animal(name);
        }

        public static Person valueOf(String s) {
            return new Person(s);
        }

        public String toString(){
            return name;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Animal{
        private String name;

        public static Animal valueOf(Person person) {
            return new Animal(person.getName());
        }
        public static Animal of(Person person) {
            return new Animal(person.getName());
        }
    }


    @Data
    private static class User{

        private Person person;
        private Animal animal;
    }
}