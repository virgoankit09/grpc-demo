package com.example.grpc.spanner;

import com.example.grpc.blog.server.BlogSpannerHelper;
import com.example.grpc.blog.server.GoogleCloudSpannerConfig;

public class SpannerTest {

    public static void main(String[] args) {
        //System.out.println("id:"+ IdGeneratorUtil.getNextIdForBlog());
       /* BlogSpannerHelper.updateUsingDml(GoogleCloudSpannerConfig.getINSTANCE().getDbClient(),
                Blog.newBuilder()
                .setId("2")
                .setTitle("updated title twice")
                .setAuthorId("akkkshshs twice")
                .setContent("this is updated twice")
                .build()
                );*/
        //BlogSpannerHelper.listBlog(GoogleCloudSpannerConfig.getINSTANCE().getDbClient());
        //BlogSpannerHelper.queryWithParameter(GoogleCloudSpannerConfig.getINSTANCE().getDbClient(), "2");
    }
}
