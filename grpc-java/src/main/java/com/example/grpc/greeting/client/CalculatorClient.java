package com.example.grpc.greeting.client;

import com.proto.calculator.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class CalculatorClient {

    public static void main(String[] args) {
        System.out.println("Hi. I am calculator client.");

        CalculatorClient client = new CalculatorClient();
        client.run();
    }

    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        //doUnaryCall(channel);
        //doServerStreaming(channel);
        //doClientStreaming(channel);
        //doBiDirectionalStreaming(channel);
        doSqRoot(channel);
        System.out.println("Closing channel");
        channel.shutdown();
    }

    private void doSqRoot(ManagedChannel channel) {

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);
        int number = -1;
        try {
            SquareRootResponse squareRootResponse = stub.squareRoot(SquareRootRequest.newBuilder()
                    .setNumber(number)
                    .build());
        } catch (StatusRuntimeException e) {
            System.out.println("Got exception for square root.");
            e.printStackTrace();
        }
    }

    private void doBiDirectionalStreaming(ManagedChannel channel) {

        CalculatorServiceGrpc.CalculatorServiceStub asyncClient = CalculatorServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<FindMaxRequest> requestObserver = asyncClient.findMax(new StreamObserver<FindMaxResponse>() {
            @Override
            public void onNext(FindMaxResponse value) {
                System.out.println("Max so far: "+ value.getResult());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Server completed.");
                latch.countDown();
            }
        });

        requestObserver.onNext(FindMaxRequest.newBuilder()
                .setNumber(1)
                .build());

        requestObserver.onNext(FindMaxRequest.newBuilder()
                .setNumber(5)
                .build());

        requestObserver.onNext(FindMaxRequest.newBuilder()
                .setNumber(3)
                .build());
        requestObserver.onNext(FindMaxRequest.newBuilder()
                .setNumber(6)
                .build());
        requestObserver.onNext(FindMaxRequest.newBuilder()
                .setNumber(2)
                .build());
        requestObserver.onNext(FindMaxRequest.newBuilder()
                .setNumber(20)
                .build());

        requestObserver.onCompleted();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doClientStreaming(ManagedChannel channel) {

        CalculatorServiceGrpc.CalculatorServiceStub ayncStub = CalculatorServiceGrpc.newStub(channel);
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<ComputeAverageRequest> requestObserver = ayncStub.computeAverage(new StreamObserver<ComputeAverageResponse>() {
            @Override
            public void onNext(ComputeAverageResponse value) {
                System.out.println("Response from server.");
                System.out.println("Average: " + value.getResult());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("server response completed");
                latch.countDown();
            }
        });

        requestObserver.onNext(ComputeAverageRequest.newBuilder()
                .setNumber(1)
                .build());

        requestObserver.onNext(ComputeAverageRequest.newBuilder()
                .setNumber(2)
                .build());

        requestObserver.onNext(ComputeAverageRequest.newBuilder()
                .setNumber(3)
                .build());

        requestObserver.onNext(ComputeAverageRequest.newBuilder()
                .setNumber(4)
                .build());

        requestObserver.onCompleted();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doServerStreaming(ManagedChannel channel) {
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        //server stream

        stub.primeNumberDecomposition(PrimeNumberDecompositionRequest.newBuilder()
                .setInputNumber(120)
                .build()).forEachRemaining(response -> System.out.println(response.getResult()));
    }

    private void doUnaryCall(ManagedChannel channel) {
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        //unary
        Input input = Input.newBuilder()
                .setNum1(3)
                .setNum2(10)
                .build();

        CalculatorRequest request = CalculatorRequest.newBuilder()
                .setInput(input)
                .build();

        CalculatorResponse response = stub.calculator(request);
        System.out.println("Response is: "+response.getResult());
    }

}
