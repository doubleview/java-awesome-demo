package com.doubleview.spring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.*;


/**
 * 使用场景
 * ClassPathBeanDefinitionScanner 287L
 */
public class SimpleMetadataReaderFactoryTest {

    @Test
    public void test() throws IOException {
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();

        //通过类名访问
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(AnnotatedElementUtilsTest.class.getName());
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        assertTrue(annotationMetadata.hasAnnotation(SpringBootApplication.class.getName()));
        assertTrue(annotationMetadata.isAnnotated(SpringBootApplication.class.getName()));
        assertTrue(annotationMetadata.isAnnotated(Configuration.class.getName()));

        Map<String, Object> map = annotationMetadata.getAnnotationAttributes(EnableAutoConfiguration.class.getName());
        assertEquals(map.get("excludeName"), new String[]{"xxx"});

        //通过资源访问
        metadataReaderFactory = new CachingMetadataReaderFactory();
        Resource resource = new ClassPathResource("com/enniu/cloud/services/gcpfeed/spring/AnnotatedElementUtilsTest.class");
        metadataReader = metadataReaderFactory.getMetadataReader(resource);
        annotationMetadata = metadataReader.getAnnotationMetadata();
        assertTrue(annotationMetadata.hasAnnotation(SpringBootApplication.class.getName()));
        assertEquals(annotationMetadata.getClassName(), AnnotatedElementUtilsTest.class.getName());
        assertFalse(annotationMetadata.isInterface());
    }

}