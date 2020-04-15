package com.example.grpc.blog.server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.proto.blog.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.bson.Document;
import org.bson.types.ObjectId;

public class BlogServiceImpl extends BlogServiceGrpc.BlogServiceImplBase {

    private MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private MongoDatabase database = mongoClient.getDatabase("mydb");
    private MongoCollection<Document> collection = database.getCollection("blog");

    @Override
    public void createBlog(CreateBlogRequest request, StreamObserver<CreateBlogResponse> responseObserver) {
        System.out.println("Recieved create blog request.");
        Blog blog = request.getBlog();
        Document doc = new Document("author_id", blog.getAuthorId())
                .append("title", blog.getTitle())
                .append("content", blog.getContent());

        System.out.println("Inserting blog..");
        //insert in db
        collection.insertOne(doc);

        //mongodb igenerated id
        String id = doc.getObjectId("_id").toString();
        System.out.println("Inserted blog.. +"+id);
        CreateBlogResponse response = CreateBlogResponse.newBuilder()
                .setBlog(Blog.newBuilder()
                .setAuthorId(blog.getAuthorId())
                .setContent(blog.getContent())
                .setId(id)
                .setTitle(blog.getTitle()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void readBlog(ReadBlogRequest request, StreamObserver<ReadBlogResponse> responseObserver) {
        System.out.println("Recieved read blog request.");
        String blogId = request.getBlogId();
        System.out.println("searching for blog.");
        Document doc = collection.find(Filters.eq("_id", new ObjectId(blogId))).first();

        if(doc == null) {
            System.out.println("blog not found.");
            responseObserver.onError(
                    Status.NOT_FOUND
                    .withDescription("Blog not found.")
                    .asRuntimeException()
            );
        } else {
            System.out.println("blog found.");
            Blog blog = documentToBlog(doc);
            responseObserver.onNext(ReadBlogResponse.newBuilder()
                    .setBlog(blog)
                    .build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void updateBlog(UpdateBlogRequest request, StreamObserver<UpdateBlogResponse> responseObserver) {

        String blogId = request.getBlog().getId();

        Document document = collection.find(Filters.eq("_id", new ObjectId(blogId))).first();

        if (document == null) {
            System.out.println("blog not found.");
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Blog not found.")
                            .asRuntimeException()
            );
        } else {
            Document replacement = new Document("author_id", request.getBlog().getAuthorId())
                    .append("title", request.getBlog().getTitle())
                    .append("content", request.getBlog().getContent())
                    .append("_id", new ObjectId(request.getBlog().getId()));

            collection.replaceOne(Filters.eq("_id", new ObjectId(blogId)), replacement);

            responseObserver.onNext(UpdateBlogResponse.newBuilder()
                    .setBlog(documentToBlog(replacement))
                    .build());
        }
        responseObserver.onCompleted();
    }

    private Blog documentToBlog(Document doc) {
        return Blog.newBuilder()
                .setAuthorId(doc.getString("author_id"))
                .setTitle(doc.getString("title"))
                .setContent(doc.getString("content"))
                .setId(doc.getObjectId("_id").toString())
                .build();
    }

    @Override
    public void deleteBlog(DeleteBlogRequest request, StreamObserver<DeleteBlogResponse> responseObserver) {
        System.out.println("Recieved delete blog request..");
        String blogId = request.getBlogId();
        DeleteResult result = null;
        try {
            result = collection.deleteOne(Filters.eq("_id", new ObjectId(blogId)));
        } catch (Exception e) {
            System.out.println("Blog not found..");
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Blog not found.")
                            .asRuntimeException()
            );
        }

        if(result.getDeletedCount() == 0) {
            System.out.println("Blog not found..");
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Blog not found.")
                            .asRuntimeException()
            );
        } else {
            responseObserver.onNext(DeleteBlogResponse.newBuilder()
                    .setBlogId(blogId)
                    .build());
            System.out.println("Blog deleted.");
        }
        responseObserver.onCompleted();
    }

    @Override
    public void listBlog(ListBlogRequest request, StreamObserver<ListBlogResponse> responseObserver) {
        System.out.println("Recieved List blog request...");

        collection.find().iterator().forEachRemaining(doc ->
            responseObserver.onNext(
                    ListBlogResponse.newBuilder()
                            .setBlog(documentToBlog(doc))
                            .build())
        );

        responseObserver.onCompleted();
    }
}
