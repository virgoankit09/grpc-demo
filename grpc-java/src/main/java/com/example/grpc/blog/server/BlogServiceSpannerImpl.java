package com.example.grpc.blog.server;

import com.proto.blog.*;
import io.grpc.stub.StreamObserver;

public class BlogServiceSpannerImpl extends BlogServiceGrpc.BlogServiceImplBase {

    @Override
    public void createBlog(CreateBlogRequest request, StreamObserver<CreateBlogResponse> responseObserver) {
        System.out.println("Recieved create blog request.");
        Blog blog = request.getBlog();

        System.out.println("Inserting blog..");

        long id = BlogSpannerHelper.writeUsingDml(GoogleCloudSpannerConfig.getINSTANCE().getDbClient(), blog);

        System.out.println("Inserted blog");
        CreateBlogResponse response = CreateBlogResponse.newBuilder()
                .setBlog(Blog.newBuilder()
                        .setAuthorId(blog.getAuthorId())
                        .setContent(blog.getContent())
                        .setId(String.valueOf(id))
                        .setTitle(blog.getTitle()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}