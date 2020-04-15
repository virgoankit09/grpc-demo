package com.example.grpc.blog.server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

import java.io.File;
import java.io.FileInputStream;

public class GoogleCloudSpannerConfig {

    private static GoogleCloudSpannerConfig INSTANCE = new GoogleCloudSpannerConfig();
    DatabaseClient dbClient;

    private GoogleCloudSpannerConfig() {
        try {
            File credentialsPath = new File("C:\\Workspace-2\\grpc-java\\src\\main\\resources\\teak-amphora-274106-7bc0884fc808.json");
            GoogleCredentials credentials;
            FileInputStream serviceAccountStream = new FileInputStream(credentialsPath);
            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);

            SpannerOptions options = SpannerOptions.newBuilder()
                    .setProjectId("teak-amphora-274106")
                    .setCredentials(credentials)
                    .build();
            Spanner spanner = options.getService();

            //String command = args[0];
            DatabaseId db = DatabaseId.of("teak-amphora-274106", "test-spanner", "testdb");
            this.dbClient = spanner.getDatabaseClient(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DatabaseClient getDbClient() {
        return dbClient;
    }

    public static GoogleCloudSpannerConfig getINSTANCE() {
        return INSTANCE;
    }

}
