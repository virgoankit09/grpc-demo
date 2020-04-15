package com.example.grpc.blog.server;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spanner.TransactionContext;
import com.google.cloud.spanner.TransactionRunner;
import com.proto.blog.Blog;

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

}
