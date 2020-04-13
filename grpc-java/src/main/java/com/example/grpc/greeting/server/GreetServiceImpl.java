package com.example.grpc.greeting.server;

import com.proto.greet.*;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        String result = "Hello " +firstName;
        //create the response
        GreetResponse greetResponse = GreetResponse.newBuilder()
                .setResult(result)
                .build();
        //send the response
        responseObserver.onNext(greetResponse);

        //complete the RPC call
        responseObserver.onCompleted();
        //super.greet(request, responseObserver);
    }

    @Override
    public void greetManyTimes(GreetManyTimesRequest request, StreamObserver<GreetManyTimesResponse> responseObserver) {

        String firstName = request.getGreeting().getFirstName();

        try {
            for(int i = 0 ; i < 10 ; i ++) {
                String result = "Hello " + firstName + ", response number:" +i;
                GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder()
                        .setResult(result)
                        .build();
                responseObserver.onNext(response);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        responseObserver.onCompleted();
        //super.greetManyTimes(request, responseObserver);
    }

    @Override
    public StreamObserver<LongGreetRequest> longGreet(StreamObserver<LongGreetResponse> responseObserver) {

        StreamObserver<LongGreetRequest> requestObserver = new StreamObserver<LongGreetRequest>() {

            String result = "";

            @Override
            public void onNext(LongGreetRequest value) {
                //client sends request
                result += "Hello "+ value.getGreeting().getFirstName() + "! ";
            }

            @Override
            public void onError(Throwable t) {
                //client sends an error
            }

            @Override
            public void onCompleted() {
                //client is done
                responseObserver.onNext(LongGreetResponse.newBuilder()
                        .setResult(result)
                        .build());

                responseObserver.onCompleted();
                //this is when we want to return the response.

            }
        };

        return requestObserver;
    }

    @Override
    public StreamObserver<GreetEveryoneRequest> greetEveryone(StreamObserver<GreetEveryoneResponse> responseObserver) {

        StreamObserver<GreetEveryoneRequest> requestObserver = new StreamObserver<GreetEveryoneRequest>() {
            @Override
            public void onNext(GreetEveryoneRequest value) {
                String response = "Hello " + value.getGreeting().getFirstName();
                responseObserver.onNext(GreetEveryoneResponse.newBuilder()
                        .setResult(response)
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
    public void greetWithDeadline(GreetWithDeadlineRequest request, StreamObserver<GreetWithDeadlineResponse> responseObserver) {
        Context context = Context.current();
        for(int i = 0; i<3 ; i++) {
            try {
                if(!context.isCancelled()) {
                    System.out.println("Sleep...");
                    Thread.sleep(100);
                } else {
                    return;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        responseObserver.onNext(GreetWithDeadlineResponse.newBuilder()
                .setResult("Hello: "+ request.getGreeting().getFirstName())
                .build());
        responseObserver.onCompleted();
    }
}
