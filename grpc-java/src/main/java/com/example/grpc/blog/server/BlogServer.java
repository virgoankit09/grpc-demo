package com.example.grpc.blog.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class BlogServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Starting server...");
        Server server = ServerBuilder.forPort(50051)
                //.addService(new BlogServiceImpl()) //mongo
                .addService(new BlogServiceSpannerImpl()) //spanner
                .addService(ProtoReflectionService.newInstance())
                .build();

        server.start();
        System.out.println("Server Started...");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Recieved shut down request.");
            server.shutdown();
            System.out.println("Successfully stopped the server.");
        }));

        server.awaitTermination();
    }

}
