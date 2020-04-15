package com.example.grpc.blog.server;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Statement;

public class IdGeneratorUtil {

    public static long getNextIdForBlog() {
        long id;
        DatabaseClient dbClient = GoogleCloudSpannerConfig.getINSTANCE().getDbClient();

        ResultSet resultSet =
                dbClient
                        .singleUse() // Execute a single read or query against Cloud Spanner.
                        .executeQuery(Statement.of("SELECT max(id) FROM blog"));
        //System.out.println(resultSet.getLong(1));
        resultSet.next();
        try {
            id = resultSet.getLong(0);
            id = id +1;
        } catch (NullPointerException e) {
            id = 1;
        }
        return id;
    }

}
