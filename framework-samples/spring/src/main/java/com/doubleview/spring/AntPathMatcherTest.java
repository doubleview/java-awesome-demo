package com.doubleview.spring;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

public class AntPathMatcherTest {

    /**
     *
     *    RequestMappingInfoHandlerMapping 132L
     *
     * ﻿       pattern中可以使用通配符，官方提供的三个通配符：
     *         ? 匹配一个字符
     *         *  匹配0个及以上字符
     *         ** 匹配0个及以上目录directories
     */
    @Test
    public void test() {
        PathMatcher pathMatcher = new AntPathMatcher();

        //﻿规则一：完全相同的字符串path和pattern，匹配成功
        assertTrue(pathMatcher.match("test", "test"));
        assertTrue(pathMatcher.match("/test", "/test"));
        assertTrue(pathMatcher.match("http://example.org", "http://example.org"));
        assertTrue(pathMatcher.match("/test/*", "/test/*"));

        //﻿规则二：path和pattern必须同时以“/”开头或者同时不以"/"开头才能匹配成功，否则匹配返回false
        assertFalse(pathMatcher.match("/test.jpg", "test.jpg"));
        assertFalse(pathMatcher.match("test", "/test"));
        assertFalse(pathMatcher.match("/test", "test"));

        //﻿规则三："?"通配符只能匹配一个任意的字符，不能匹配多个；可以写多个"?"匹配多个字符；"?"的位置不限制
        assertTrue(pathMatcher.match("t?st", "test"));
        assertTrue(pathMatcher.match("??st", "test"));
        assertTrue(pathMatcher.match("tes?", "test"));
        assertTrue(pathMatcher.match("te??", "test"));
        assertTrue(pathMatcher.match("?es?", "test"));
        assertTrue(pathMatcher.match("/?es?", "/test"));
        assertTrue(pathMatcher.match("/?es?/*", "/test/*"));

        assertFalse(pathMatcher.match("tes?", "tes"));
        assertFalse(pathMatcher.match("tes?", "testt"));
        assertFalse(pathMatcher.match("tes?", "tsst"));

        //规则四："?"不匹配"/"，可以匹配"?"，"*"
        assertTrue(pathMatcher.match("/?", "/a"));
        assertTrue(pathMatcher.match("/?/a", "/a/a"));
        assertTrue(pathMatcher.match("/??/a", "/aa/a"));
        assertTrue(pathMatcher.match("/a/??", "/a/bb"));
        assertFalse(pathMatcher.match("/a?", "/a/"));
        assertFalse(pathMatcher.match("a?/a", "a//a"));

        assertTrue(pathMatcher.match("/a/?", "/a/?"));//规则一
        assertTrue(pathMatcher.match("/a/?", "/a/*"));
        assertFalse(pathMatcher.match("/a/?", "/a/**"));

        //规则五："*"可以匹配0个或多个字符，可以出现多次，位置不限
        assertTrue(pathMatcher.match("*", "test"));
        assertTrue(pathMatcher.match("test*", "test"));
        assertTrue(pathMatcher.match("test*", "testTest"));
        assertTrue(pathMatcher.match("test/*", "test/Test"));
        assertTrue(pathMatcher.match("test/*", "test/t"));
        assertTrue(pathMatcher.match("test/*", "test/"));

        assertTrue(pathMatcher.match("*test*", "AnothertestTest"));
        assertTrue(pathMatcher.match("*test", "Anothertest"));
        assertTrue(pathMatcher.match("*.*", "test."));
        assertTrue(pathMatcher.match("*.*", "test.test"));
        assertTrue(pathMatcher.match("*.*", "test.test.test"));

        //﻿规则六："*"不匹配"/"，但可以匹配"?"，"*"和"**"
        assertFalse(pathMatcher.match("test*", "test/"));
        assertFalse(pathMatcher.match("test*", "test/t"));
        assertFalse(pathMatcher.match("test/*", "test"));

        assertTrue(pathMatcher.match("test*", "test*"));
        assertTrue(pathMatcher.match("test*", "test**"));
        assertTrue(pathMatcher.match("test*", "test?"));


        //﻿规则七："**"匹配0个或多个目录
        assertTrue(pathMatcher.match("/**", "/"));
        assertTrue(pathMatcher.match("/**", "/testing"));
        assertTrue(pathMatcher.match("/**", "/testing/testing"));

        assertTrue(pathMatcher.match("/bla/**/bla", "/bla/bla"));
        assertTrue(pathMatcher.match("/bla/**/bla", "/bla/testing/bla"));
        assertTrue(pathMatcher.match("/bla/**/bla", "/bla/testing/testing/bla/bla"));

        assertTrue(pathMatcher.match("/**/*bla", "/bla/bla/bla/bla"));
        assertFalse(pathMatcher.match("/bla*bla/test", "/blaXXXbl/test"));


        //﻿规则八："/**"，"**"与其他通配符的关系
        assertTrue(pathMatcher.match("test/**", "test/**"));
        assertTrue(pathMatcher.match("test/**", "test"));
        assertTrue(pathMatcher.match("test/**", "test/"));
        assertFalse(pathMatcher.match("test/**", "test?"));
        assertFalse(pathMatcher.match("test/**", "test*"));
        assertFalse(pathMatcher.match("test/**", "test**"));

        assertTrue(pathMatcher.match("test**", "test*"));
        assertTrue(pathMatcher.match("test**", "test**"));
        assertTrue(pathMatcher.match("test**", "test?"));
        assertFalse(pathMatcher.match("test**", "test/"));
        assertFalse(pathMatcher.match("test**", "test/t"));

        //抽取模板变量
        String pattern = "/gcp-feed/api/v1/feeds/{feed-id}/comments/{comment-id}";
        String path = "/gcp-feed/api/v1/feeds/9000/comments/1000";
        assertTrue(pathMatcher.match(pattern, path));
        Map<String, String> paramMap = pathMatcher.extractUriTemplateVariables(pattern, path);
        assertEquals(paramMap.get("feed-id"), "9000");
        assertEquals(paramMap.get("comment-id"), "1000");

        //抽取路径
        assertEquals(pathMatcher.extractPathWithinPattern("/docs/*", "/docs/cvs/commit"), "cvs/commit");
        assertEquals(pathMatcher.extractPathWithinPattern("/docs/cvs/*.html", "/docs/cvs/commit.html"), "commit.html");
        assertEquals(pathMatcher.extractPathWithinPattern("*.html", "/docs/cvs/commit.html"), "xxxzxxx");

        //正则表达式匹配
        pattern = "/gcp-feed/api/v1/feeds/{feed-id:\\d+}/comments/{comment-id:\\d+}";
        assertTrue(pathMatcher.match(pattern, "/gcp-feed/api/v1/feeds/9000/comments/1000"));
        assertFalse(pathMatcher.match(pattern, "/gcp-feed/api/v1/feeds/abcd/comments/abcd"));
    }
}
