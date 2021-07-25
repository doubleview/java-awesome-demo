package com.doubleview.spring;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.MultiValueMap;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * RequestMappingHandlerMapping 205L
 */
@SpringBootApplication(scanBasePackageClasses = {AnnotatedElementUtilsTest.class}, excludeName = {"xxx"})
public class AnnotatedElementUtilsTest {

    @Test
    public void test() {
        AnnotationAttributes annotationAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(AnnotatedElementUtilsTest.class, "org.springframework.context.annotation.ComponentScan", false, true);
        System.out.println(annotationAttributes);


        //findMergedAnnotation
        ComponentScan componentScan = AnnotatedElementUtils.findMergedAnnotation(AnnotatedElementUtilsTest.class, ComponentScan.class);
        assertEquals(componentScan.basePackageClasses(), new Class[]{AnnotatedElementUtilsTest.class});

        EnableAutoConfiguration enableAutoConfiguration = AnnotatedElementUtils.findMergedAnnotation(AnnotatedElementUtilsTest.class, EnableAutoConfiguration.class);
        assertEquals(enableAutoConfiguration.excludeName(), new String[]{"xxx"});

        //findMergedAnnotationAttributes
        AnnotationAttributes componentScanAnnotation = AnnotatedElementUtils.findMergedAnnotationAttributes(AnnotatedElementUtilsTest.class, ComponentScan.class, false, true);
        assertEquals(componentScanAnnotation.getClassArray("basePackageClasses"), new Class[]{AnnotatedElementUtilsTest.class});

        //hasAnnotation  or  isAnnotated
        assertTrue(AnnotatedElementUtils.hasAnnotation(AnnotatedElementUtilsTest.class, ComponentScan.class));
        assertTrue(AnnotatedElementUtils.isAnnotated(AnnotatedElementUtilsTest.class, SpringBootApplication.class));
        assertTrue(AnnotatedElementUtils.isAnnotated(AnnotatedElementUtilsTest.class, ComponentScan.class));

        //hasMetaAnnotationTypes
        assertFalse(AnnotatedElementUtils.hasMetaAnnotationTypes(AnnotatedElementUtilsTest.class, SpringBootApplication.class));
        assertTrue(AnnotatedElementUtils.hasMetaAnnotationTypes(AnnotatedElementUtilsTest.class, ComponentScan.class));

        // getMetaAnnotationTypes
        Set<String> metaAnnotationTypes = AnnotatedElementUtils.getMetaAnnotationTypes(AnnotatedElementUtilsTest.class, SpringBootApplication.class);
        assertThat(metaAnnotationTypes, hasItem(SpringBootConfiguration.class.getName()));
        assertThat(metaAnnotationTypes, hasItem(Configuration.class.getName()));

        // getAllAnnotationAttributes
        MultiValueMap<String, Object> multiValueMap = AnnotatedElementUtils.getAllAnnotationAttributes(AnnotatedElementUtilsTest.class, Import.class.getName());
        assertThat(multiValueMap.size(), is(1));
        assertThat(multiValueMap.keySet(), hasItem("value"));
        assertThat(multiValueMap.get("value").size(), is(2));
    }
}
