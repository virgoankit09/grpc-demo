package com.example.grpc.blog.server;

import com.mongodb.client.model.Filters;
import com.proto.blog.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    @Override
    public void updateBlog(UpdateBlogRequest request, StreamObserver<UpdateBlogResponse> responseObserver) {

        System.out.println("Recieved update blog request.");
        Blog blog = request.getBlog();

        System.out.println("updating blog..");

        BlogSpannerHelper.updateUsingDml(GoogleCloudSpannerConfig.getINSTANCE().getDbClient(), blog);

        System.out.println("updated blog");
        UpdateBlogResponse response = UpdateBlogResponse.newBuilder()
                .setBlog(Blog.newBuilder()
                        .setAuthorId(blog.getAuthorId())
                        .setContent(blog.getContent())
                        .setId(String.valueOf(blog.getId()))
                        .setTitle(blog.getTitle()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void deleteBlog(DeleteBlogRequest request, StreamObserver<DeleteBlogResponse> responseObserver) {
        System.out.println("Received Delete blog request..");

        String id = request.getBlogId();
        BlogSpannerHelper.deleteUsingDml(GoogleCloudSpannerConfig.getINSTANCE().getDbClient(), id);

        responseObserver.onNext(DeleteBlogResponse.newBuilder()
                .setBlogId(id)
                .build());
        System.out.println("Deleted blog.");
        responseObserver.onCompleted();
    }

    @Override
    public void listBlog(ListBlogRequest request, StreamObserver<ListBlogResponse> responseObserver) {

        System.out.println("Recieved List blog request...");
        BlogSpannerHelper.listBlog(GoogleCloudSpannerConfig.getINSTANCE().getDbClient(), responseObserver);

        responseObserver.onCompleted();

    }

    @Override
    public void readBlog(ReadBlogRequest request, StreamObserver<ReadBlogResponse> responseObserver) {
        System.out.println("Recieved read blog request.");
        String blogId = request.getBlogId();
        System.out.println("searching for blog.");

        BlogSpannerHelper.queryWithParameter(GoogleCloudSpannerConfig.getINSTANCE().getDbClient(), responseObserver, blogId);

        responseObserver.onCompleted();
    }
}