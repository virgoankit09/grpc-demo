package com.example.grpc.greeting.server;

import com.proto.calculator.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void calculator(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {

        Input input = request.getInput();
        int num1 = input.getNum1();
        int num2 = input.getNum2();

        int result = num1 + num2;

        CalculatorResponse response = CalculatorResponse.newBuilder()
                    .setResult(result)
                    .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        //super.calculator(request, responseObserver);
    }

    @Override
    public void primeNumberDecomposition(PrimeNumberDecompositionRequest request, StreamObserver<PrimeNumberDecompositionResponse> responseObserver) {

        int N = request.getInputNumber();
        int k = 2;
        while (N > 1) {
            if (N % k == 0) {
                // if k evenly divides into N
                responseObserver.onNext(PrimeNumberDecompositionResponse.newBuilder()
                        .setResult(k)
                        .build());      // this is a factor
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                N = N / k;   // divide N by k so that we have the rest of the number left.
            } else {
                k = k + 1;
            }
        }
        responseObserver.onCompleted();
        //super.primeNumberDecomposition(request, responseObserver);
    }

    @Override
    public StreamObserver<ComputeAverageRequest> computeAverage(StreamObserver<ComputeAverageResponse> responseObserver) {

        StreamObserver<ComputeAverageRequest> requestObserver = new StreamObserver<ComputeAverageRequest>() {

            int totalSum = 0;
            int counter = 0;

            @Override
            public void onNext(ComputeAverageRequest value) {
                totalSum += value.getNumber();
                counter++;
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                double result = (double)totalSum/counter;
                responseObserver.onNext(ComputeAverageResponse.newBuilder()
                        .setResult(result)
                        .build());
                responseObserver.onCompleted();
            }
        };
        return requestObserver;
    }

    @Override
    public StreamObserver<FindMaxRequest> findMax(StreamObserver<FindMaxResponse> responseObserver) {

        StreamObserver<FindMaxRequest> requestObserver = new StreamObserver<FindMaxRequest>() {

            List<Integer> list = new ArrayList<>();

            @Override
            public void onNext(FindMaxRequest value) {
                list.add(value.getNumber());
                responseObserver.onNext(FindMaxResponse.newBuilder()
                        .setResult(list.stream().max(Comparator.comparing(Integer::valueOf)).get())
                        .build());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
        return requestObserver;
    }

    @Override
    public void squareRoot(SquareRootRequest request, StreamObserver<SquareRootResponse> responseObserver) {
        int number = request.getNumber();
        if(number >= 0) {
            responseObserver.onNext(SquareRootResponse.newBuilder()
                    .setRootNumber(Math.sqrt(number))
                    .build());
        } else {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("The number sent is not positive")
                    .asRuntimeException());
        }
    }
}
