package com.doubleview.spring;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;

import java.net.URI;

import static org.testng.Assert.assertEquals;

/**
 * RestTemplate 620L
 */
public class UriComponentsBuilderTest {

    @Test
    public void uriComponentsBuilderTest() {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://api.u51.com/gcp-feed/api/v1/feeds/{feed-id}/comments/{comment-id}").build();
        URI uri = uriComponents.expand(100, 200).toUri();
        assertEquals(uri.toString(), "http://api.u51.com/gcp-feed/api/v1/feeds/100/comments/200");

        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.u51.com")
                .path("/gcp-feed/api/v1/feeds/{feed-id}/comments/{comment-id}")
                .queryParam("name", "胡成超")
                .build()
                .expand(100, 200).encode();
        assertEquals(uriComponents.toUriString(), "http://api.u51.com/gcp-feed/api/v1/feeds/100/comments/200?name=%E8%83%A1%E6%88%90%E8%B6%85");
    }

    @Test
    public void uriComponentsBuilderTest2() {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://api.u51.com/gcp-feed/api/v1/feeds?name=胡成超")
                .replaceQueryParam("name", "胡家三少")
                .query("feedType=1&feedId=1111")
                .build();

        assertEquals(uriComponents.toUriString(), "http://api.u51.com/gcp-feed/api/v1/feeds?name=胡家三少&feedType=1&feedId=1111");
    }

//    @Test
//    public void mvcUriComponentsBuilderTest() {
//        UriComponentsBuilder baseUrlBuilder = UriComponentsBuilder.fromUriString("http://api.u51.com");
//
//        UriComponents uriComponents = MvcUriComponentsBuilder.relativeTo(baseUrlBuilder)
//                .withMethodName(CommentSuggestController.class, "getAllCommentInFeed", 100L, 1, 1000L, 1, 0, 20, 0L)
//                .build();
//
//        assertEquals(uriComponents.toUriString(), "http://api.u51.com/gcp-feed/api/v1/me/1/feeds/1000/comments?sortBy=1&pageIndex=0&pageSize=20&publishTime=0");
//
//        uriComponents = MvcUriComponentsBuilder.relativeTo(baseUrlBuilder)
//                .withMethodCall(MvcUriComponentsBuilder.on(CommentSuggestController.class).getAllCommentInFeed(100L, 1, 1000L, 1, 0, 20, 0L))
//                .build();
//        assertEquals(uriComponents.toUriString(), "http://api.u51.com/gcp-feed/api/v1/me/1/feeds/1000/comments?sortBy=1&pageIndex=0&pageSize=20&publishTime=0");
//    }
}