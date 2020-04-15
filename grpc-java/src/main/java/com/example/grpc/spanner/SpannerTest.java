package com.example.grpc.spanner;

import com.example.grpc.blog.server.IdGeneratorUtil;

public class SpannerTest {

    public static void main(String[] args) {
        System.out.println("id:"+ IdGeneratorUtil.getNextIdForBlog());
    }
}
