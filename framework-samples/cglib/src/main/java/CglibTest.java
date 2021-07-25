/**
 * @author huchengchao.
 * @description:
 * @date: 2018-06-27 下午7:10
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class CglibTest {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        FeedService feedService = (FeedService) cglibProxy.getProxy(FeedService.class);
        System.out.println(feedService.getClass());
        feedService.businessMethod();
    }

}
