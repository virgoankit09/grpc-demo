package com.example.grpc.greeting.client;

import com.proto.greet.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Hello. I am a GRPC client.");
        GreetingClient client = new GreetingClient();
        client.run();

        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);
    }

    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        //doUnaryCall(channel);
        //doServerStreamingCall(channel);
        //doClientStreamingCall(channel);
        //doBiDirectionalStreaming(channel);
        doUnaryCallWithDeadline(channel);
        System.out.println("Shutting down channel.");
        channel.shutdown();
    }

    private void doUnaryCallWithDeadline(ManagedChannel channel) {

        GreetServiceGrpc.GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);
        try {
            System.out.println("Sending request with 5000ms.");
           GreetWithDeadlineResponse response = stub.withDeadline(Deadline.after(5000, TimeUnit.MILLISECONDS)).greetWithDeadline(GreetWithDeadlineRequest.newBuilder()
                    .setGreeting(Greeting.newBuilder()
                            .setFirstName("Ankit")
                            .build())
                    .build());
            System.out.println("Response from server: "+response.getResult());
        } catch (StatusRuntimeException e) {
            if(e.getStatus() == Status.DEADLINE_EXCEEDED) {
                System.out.println("Deadline exceeded. we dont want the answer.");
            } else {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("Sending request with 100ms.");
            GreetWithDeadlineResponse response = stub.withDeadline(Deadline.after(100, TimeUnit.MILLISECONDS)).greetWithDeadline(GreetWithDeadlineRequest.newBuilder()
                    .setGreeting(Greeting.newBuilder()
                            .setFirstName("Ankit")
                            .build())
                    .build());
            System.out.println("Response from server: "+response.getResult());
        } catch (StatusRuntimeException e) {
            if (e.getStatus() == Status.DEADLINE_EXCEEDED) {
                System.out.println("Deadline exceeded. we dont want the answer.");
            } else {
                e.printStackTrace();
            }
        }
    }

    private void doBiDirectionalStreaming(ManagedChannel channel) {

        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<GreetEveryoneRequest> requestObserver = asyncClient.greetEveryone(new StreamObserver<GreetEveryoneResponse>() {
            @Override
            public void onNext(GreetEveryoneResponse value) {
                System.out.println("Response of server: "+ value.getResult());
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server is done.");
                latch.countDown();
            }
        });

        requestObserver.onNext(GreetEveryoneRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Ankit").build())
                .build());

        requestObserver.onNext(GreetEveryoneRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Akshay").build())
                .build());

        requestObserver.onNext(GreetEveryoneRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Pulkit").build())
                .build());
        requestObserver.onCompleted();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doClientStreamingCall(ManagedChannel channel) {
        //async client
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<LongGreetRequest> requestObserver = asyncClient.longGreet(new StreamObserver<LongGreetResponse>() {
            @Override
            public void onNext(LongGreetResponse value) {
                //we get a response from server
                System.out.println("Received response from server.");
                System.out.println(value.getResult());
                //onNext will be called only once as server will send only one response.
            }

            @Override
            public void onError(Throwable t) {
                //error from server
            }

            @Override
            public void onCompleted() {
                //server done sedning data
                System.out.println("Server has completed sedning response.");
                //onCompleted will be called after onNext
                latch.countDown();
            }
        });

        requestObserver.onNext(LongGreetRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Ankit").build())
                .build());

        requestObserver.onNext(LongGreetRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Akshay").build())
                .build());
        requestObserver.onNext(LongGreetRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Akash").build())
                .build());
        requestObserver.onCompleted();
        try {
            latch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doServerStreamingCall(ManagedChannel channel) {
        //server streaming
        //greeting service client
        GreetServiceGrpc.GreetServiceBlockingStub greetingClient = GreetServiceGrpc.newBlockingStub(channel);
        GreetManyTimesRequest request = GreetManyTimesRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Ankit").setLastName("Garg").build())
                .build();

        //stream the response in blocking manner
        greetingClient.greetManyTimes(request).
                forEachRemaining(response -> System.out.println(response.getResult()));
    }

    private void doUnaryCall(ManagedChannel channel) {
        //greeting service client
        GreetServiceGrpc.GreetServiceBlockingStub greetingClient = GreetServiceGrpc.newBlockingStub(channel);
        //unary
        //protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Ankit")
                .setLastName("Garg")
                .build();
        //creating request
        GreetRequest request = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();
        //call the rpc
        GreetResponse response = greetingClient.greet(request);
        System.out.println(response.getResult());

    }

}
