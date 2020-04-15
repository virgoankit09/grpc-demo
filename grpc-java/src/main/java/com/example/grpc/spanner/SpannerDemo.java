package com.example.grpc.spanner;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.spanner.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SpannerDemo {

    public static void main(String[] args) throws IOException {
        File credentialsPath = new File("C:\\Workspace-2\\grpc-java\\src\\main\\resources\\teak-amphora-274106-7bc0884fc808.json");
        GoogleCredentials credentials;
        FileInputStream serviceAccountStream = new FileInputStream(credentialsPath);
        credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);

        SpannerOptions options = SpannerOptions.newBuilder()
                .setProjectId("teak-amphora-274106")
                .setCredentials(credentials)
                .build();
        Spanner spanner = options.getService();
        DatabaseClient dbClient = null;
        try {
            //String command = args[0];
            DatabaseId db = DatabaseId.of("teak-amphora-274106", "test-spanner", "testdb");
            dbClient = spanner.getDatabaseClient(db);
            System.out.println(dbClient);
            writeUsingDml(dbClient);

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void writeUsingDml(DatabaseClient dbClient) {
        // Insert 4 singer records
        dbClient
                .readWriteTransaction()
                .run(
                        new TransactionRunner.TransactionCallable<Void>() {
                            @Override
                            public Void run(TransactionContext transaction) throws Exception {
                                String sql =
                                        "INSERT INTO blog (id, author_id, title, content) VALUES "
                                                + "('12', 'Melissa', 'first post', 'this is body 1'), "
                                                + "('15', 'Dylan', 'second post', 'this is body 2')";
                                long rowCount = transaction.executeUpdate(Statement.of(sql));
                                System.out.printf("%d records inserted.\n", rowCount);
                                return null;
                            }
                        });
    }

}
