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
            File credentialsPath = new File("C:\\Workspace-2\\grpc-java\\src\\main\\resources\\linen-marking-274806-2e0a3699f7e7.json");
            GoogleCredentials credentials;
            FileInputStream serviceAccountStream = new FileInputStream(credentialsPath);
            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);

            SpannerOptions options = SpannerOptions.newBuilder()
                    .setProjectId("linen-marking-274806")
                    .setCredentials(credentials)
                    .build();
            Spanner spanner = options.getService();

            //String command = args[0];
            DatabaseId db = DatabaseId.of("linen-marking-274806", "test-spanner", "testdb");
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
