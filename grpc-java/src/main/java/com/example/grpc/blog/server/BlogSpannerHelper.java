package com.example.grpc.blog.server;

import com.google.cloud.spanner.*;
import com.proto.blog.Blog;
import com.proto.blog.ListBlogResponse;
import com.proto.blog.ReadBlogResponse;
import io.grpc.stub.StreamObserver;

public class BlogSpannerHelper {

    public static long writeUsingDml(DatabaseClient dbClient, Blog blog) {

        long id = IdGeneratorUtil.getNextIdForBlog();
        // Insert 4 singer records
        dbClient
                .readWriteTransaction()
                .run(
                        new TransactionRunner.TransactionCallable<Void>() {
                            @Override
                            public Void run(TransactionContext transaction) throws Exception {
                                String sql =
                                        "INSERT INTO blog (id, author_id, title, content) VALUES "
                                                + "("+id+", '"+blog.getAuthorId()+"', '" +
                                                blog.getTitle()+"', '"+blog.getContent()+"')";
                                long rowCount = transaction.executeUpdate(Statement.of(sql));
                                System.out.printf("%d records inserted.\n", rowCount);
                                return null;
                            }
                        });
        return id;
    }

    public static void updateUsingDml(DatabaseClient dbClient, Blog blog) {

        dbClient
                .readWriteTransaction()
                .run(
                        new TransactionRunner.TransactionCallable<Void>() {
                            @Override
                            public Void run(TransactionContext transaction) throws Exception {
                                String sql =
                                        "UPDATE blog SET author_id = '"+blog.getAuthorId()+"' "
                                                +", title='"+blog.getTitle()+"'"
                                                +", content='"+blog.getContent()+"'"
                                                + "WHERE id = "+blog.getId();
                                long rowCount = transaction.executeUpdate(Statement.of(sql));
                                System.out.printf("%d record updated.\n", rowCount);
                                return null;
                            }
                        });
    }

    static void deleteUsingDml(DatabaseClient dbClient, String id) {
        dbClient
                .readWriteTransaction()
                .run(
                        new TransactionRunner.TransactionCallable<Void>() {
                            @Override
                            public Void run(TransactionContext transaction) throws Exception {
                                String sql = "DELETE FROM blog WHERE id = "+Long.parseLong(id);
                                long rowCount = transaction.executeUpdate(Statement.of(sql));
                                System.out.printf("%d record deleted.\n", rowCount);
                                return null;
                            }
                        });
    }

    public static void listBlog(DatabaseClient dbClient, StreamObserver<ListBlogResponse> responseObserver) {
        try (ResultSet resultSet =
                     dbClient
                             .singleUse() // Execute a single read or query against Cloud Spanner.
                             .executeQuery(Statement.of("SELECT id, author_id, title, content FROM blog"))) {
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getLong(0)+". author: "+ resultSet.getString(1)+". title: "+resultSet.getString(2)
                        +". content:"+resultSet.getString(3));
                responseObserver.onNext(
                        ListBlogResponse.newBuilder()
                                .setBlog(Blog.newBuilder()
                                .setId(String.valueOf(resultSet.getLong(0)))
                                .setAuthorId(resultSet.getString(1))
                                .setTitle(resultSet.getString(2))
                                .setContent(resultSet.getString(3))
                                .build())
                                .build());

            }
        }
    }

    public static void queryWithParameter(DatabaseClient dbClient, StreamObserver<ReadBlogResponse> responseObserver, String id) {
        Statement statement =
                Statement.newBuilder(
                        "SELECT id, author_id, title, content "
                                + "FROM blog "
                                + "WHERE id = @id")
                        .bind("id")
                        .to(Long.parseLong(id))
                        .build();
        try (ResultSet resultSet = dbClient.singleUse().executeQuery(statement)) {
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getLong(0)+". author: "+ resultSet.getString(1)+". title: "+resultSet.getString(2)
                                +". content:"+resultSet.getString(3));
                responseObserver.onNext(
                        ReadBlogResponse.newBuilder()
                                .setBlog(Blog.newBuilder()
                                        .setId(String.valueOf(resultSet.getLong(0)))
                                        .setAuthorId(resultSet.getString(1))
                                        .setTitle(resultSet.getString(2))
                                        .setContent(resultSet.getString(3))
                                        .build())
                                .build());


            }
        }
    }

}
