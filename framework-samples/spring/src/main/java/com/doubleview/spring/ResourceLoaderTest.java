package com.doubleview.spring;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * ClassPathScanningCandidateComponentProvider 278L
 */
public class ResourceLoaderTest {

    @Test
    public void resourceLoaderTest() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        //类路径下
        Resource classPathResource = resourceLoader.getResource("classpath:com/enniu/cloud/services/gcpfeed/spring/ResourceLoaderTest.class");
        assertTrue(classPathResource.exists());

        classPathResource = resourceLoader.getResource("/com/enniu/cloud/services/gcpfeed/spring/ResourceLoaderTest.class");
        assertTrue(classPathResource.exists());

        classPathResource = resourceLoader.getResource("com/enniu/cloud/services/gcpfeed/spring/ResourceLoaderTest.class");
        assertTrue(classPathResource.exists());

        //文件系统
        Resource fileResource = resourceLoader.getResource("file:/Users/huchengchao/.config");
        assertTrue(fileResource.exists());

        //网络路径下
        Resource httpResource = resourceLoader.getResource("http://www.baidu.com");
        assertTrue(httpResource.exists());
        assertTrue(httpResource.contentLength() > 0L);
    }

    @Test
    public void resourcePatternResolverTest() throws IOException {
        //路径模式匹配
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath:com/enniu/cloud/services/gcpfeed/spring/*.class");
        assertTrue(resources.length > 0);
    }
}