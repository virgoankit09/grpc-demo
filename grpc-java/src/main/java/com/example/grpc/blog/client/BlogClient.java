package com.example.grpc.blog.client;

import com.proto.blog.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class BlogClient {

    public static void main(String[] args) {
        System.out.println("Hello! I am a grpc client for blog.");
        BlogClient client = new BlogClient();
        
        client.run();
    }

    private void run() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        //createBlog(channel);
        //readBlog(channel);
        //updateBlog(channel);
        //deleteBlog(channel);
        listBlog(channel);
        //shutting down the channel.
        channel.shutdown();

    }

    private void listBlog(ManagedChannel channel) {
        System.out.println("List blog request..");
        BlogServiceGrpc.BlogServiceBlockingStub client = BlogServiceGrpc.newBlockingStub(channel);

        Iterator<ListBlogResponse> listBlogResponseIterator = client.listBlog(ListBlogRequest.newBuilder().build());

        listBlogResponseIterator.forEachRemaining(blog -> System.out.println(blog.getBlog().toString()));
    }

    private void deleteBlog(ManagedChannel channel) {
        System.out.println("Delete blog request..");
        BlogServiceGrpc.BlogServiceBlockingStub client = BlogServiceGrpc.newBlockingStub(channel);
        String blogId = "5e956d882d356e2a50d1aa70";
        DeleteBlogResponse deleteBlogResponse = client.deleteBlog(DeleteBlogRequest.newBuilder()
                .setBlogId(blogId)
                .build());

        System.out.println("Deleted blog."+blogId);

        System.out.println("trying to delete same blog again. this should return not found..");
        DeleteBlogResponse deleteBlogResponse2 = client.deleteBlog(DeleteBlogRequest.newBuilder()
                .setBlogId(blogId)
                .build());
    }

    private void updateBlog(ManagedChannel channel) {
        System.out.println("Update blog request..");
        BlogServiceGrpc.BlogServiceBlockingStub client = BlogServiceGrpc.newBlockingStub(channel);

        Blog newBlog = Blog.newBuilder()
                .setAuthorId("Ankit Garg")
                .setContent("updated the blog author.")
                .setTitle("updated Blog")
                .setId("5e956d882d356e2a50d1aa70")
                .build();

        UpdateBlogResponse updateBlogResponse = client.updateBlog(UpdateBlogRequest.newBuilder()
                .setBlog(newBlog)
                .build());

        System.out.println("Updated blog response:" +updateBlogResponse.getBlog().toString());
    }

    private void createBlog(ManagedChannel channel) {
        BlogServiceGrpc.BlogServiceBlockingStub client = BlogServiceGrpc.newBlockingStub(channel);
        Blog blog = Blog.newBuilder()
                .setAuthorId("Ankit")
                .setContent("This is my first blog")
                .setTitle("New Blog")
                .build();

        CreateBlogResponse blogResponse = client.createBlog(CreateBlogRequest.newBuilder()
                .setBlog(blog)
                .build());

        System.out.println("Blog created: "+blogResponse.getBlog().toString());
    }

    private void readBlog(ManagedChannel channel) {
        BlogServiceGrpc.BlogServiceBlockingStub client = BlogServiceGrpc.newBlockingStub(channel);
        ReadBlogResponse readBlogResponse = client.readBlog(ReadBlogRequest.newBuilder()
                .setBlogId("5e956d882d356e2a50d1aa70")
                .build());

        System.out.println("Read blog response: "+ readBlogResponse.getBlog().toString());

        System.out.println("Trying the read blog api with fake id...");

        ReadBlogResponse readBlogResponseNotFound = client.readBlog(ReadBlogRequest.newBuilder()
                .setBlogId("5e956d882d356e2a50d1aa55")
                .build());
    }

}
