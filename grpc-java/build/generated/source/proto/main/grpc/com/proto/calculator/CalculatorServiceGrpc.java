package com.proto.calculator;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: calculator/calculator.proto")
public final class CalculatorServiceGrpc {

  private CalculatorServiceGrpc() {}

  public static final String SERVICE_NAME = "calculator.CalculatorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.calculator.CalculatorRequest,
      com.proto.calculator.CalculatorResponse> getCalculatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Calculator",
      requestType = com.proto.calculator.CalculatorRequest.class,
      responseType = com.proto.calculator.CalculatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.calculator.CalculatorRequest,
      com.proto.calculator.CalculatorResponse> getCalculatorMethod() {
    io.grpc.MethodDescriptor<com.proto.calculator.CalculatorRequest, com.proto.calculator.CalculatorResponse> getCalculatorMethod;
    if ((getCalculatorMethod = CalculatorServiceGrpc.getCalculatorMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getCalculatorMethod = CalculatorServiceGrpc.getCalculatorMethod) == null) {
          CalculatorServiceGrpc.getCalculatorMethod = getCalculatorMethod =
              io.grpc.MethodDescriptor.<com.proto.calculator.CalculatorRequest, com.proto.calculator.CalculatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Calculator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.CalculatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.CalculatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("Calculator"))
              .build();
        }
      }
    }
    return getCalculatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.calculator.PrimeNumberDecompositionRequest,
      com.proto.calculator.PrimeNumberDecompositionResponse> getPrimeNumberDecompositionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrimeNumberDecomposition",
      requestType = com.proto.calculator.PrimeNumberDecompositionRequest.class,
      responseType = com.proto.calculator.PrimeNumberDecompositionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.calculator.PrimeNumberDecompositionRequest,
      com.proto.calculator.PrimeNumberDecompositionResponse> getPrimeNumberDecompositionMethod() {
    io.grpc.MethodDescriptor<com.proto.calculator.PrimeNumberDecompositionRequest, com.proto.calculator.PrimeNumberDecompositionResponse> getPrimeNumberDecompositionMethod;
    if ((getPrimeNumberDecompositionMethod = CalculatorServiceGrpc.getPrimeNumberDecompositionMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getPrimeNumberDecompositionMethod = CalculatorServiceGrpc.getPrimeNumberDecompositionMethod) == null) {
          CalculatorServiceGrpc.getPrimeNumberDecompositionMethod = getPrimeNumberDecompositionMethod =
              io.grpc.MethodDescriptor.<com.proto.calculator.PrimeNumberDecompositionRequest, com.proto.calculator.PrimeNumberDecompositionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrimeNumberDecomposition"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.PrimeNumberDecompositionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.PrimeNumberDecompositionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("PrimeNumberDecomposition"))
              .build();
        }
      }
    }
    return getPrimeNumberDecompositionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.calculator.ComputeAverageRequest,
      com.proto.calculator.ComputeAverageResponse> getComputeAverageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ComputeAverage",
      requestType = com.proto.calculator.ComputeAverageRequest.class,
      responseType = com.proto.calculator.ComputeAverageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.calculator.ComputeAverageRequest,
      com.proto.calculator.ComputeAverageResponse> getComputeAverageMethod() {
    io.grpc.MethodDescriptor<com.proto.calculator.ComputeAverageRequest, com.proto.calculator.ComputeAverageResponse> getComputeAverageMethod;
    if ((getComputeAverageMethod = CalculatorServiceGrpc.getComputeAverageMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getComputeAverageMethod = CalculatorServiceGrpc.getComputeAverageMethod) == null) {
          CalculatorServiceGrpc.getComputeAverageMethod = getComputeAverageMethod =
              io.grpc.MethodDescriptor.<com.proto.calculator.ComputeAverageRequest, com.proto.calculator.ComputeAverageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ComputeAverage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.ComputeAverageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.ComputeAverageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("ComputeAverage"))
              .build();
        }
      }
    }
    return getComputeAverageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.calculator.FindMaxRequest,
      com.proto.calculator.FindMaxResponse> getFindMaxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindMax",
      requestType = com.proto.calculator.FindMaxRequest.class,
      responseType = com.proto.calculator.FindMaxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.calculator.FindMaxRequest,
      com.proto.calculator.FindMaxResponse> getFindMaxMethod() {
    io.grpc.MethodDescriptor<com.proto.calculator.FindMaxRequest, com.proto.calculator.FindMaxResponse> getFindMaxMethod;
    if ((getFindMaxMethod = CalculatorServiceGrpc.getFindMaxMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getFindMaxMethod = CalculatorServiceGrpc.getFindMaxMethod) == null) {
          CalculatorServiceGrpc.getFindMaxMethod = getFindMaxMethod =
              io.grpc.MethodDescriptor.<com.proto.calculator.FindMaxRequest, com.proto.calculator.FindMaxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindMax"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.FindMaxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.FindMaxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("FindMax"))
              .build();
        }
      }
    }
    return getFindMaxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.calculator.SquareRootRequest,
      com.proto.calculator.SquareRootResponse> getSquareRootMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SquareRoot",
      requestType = com.proto.calculator.SquareRootRequest.class,
      responseType = com.proto.calculator.SquareRootResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.calculator.SquareRootRequest,
      com.proto.calculator.SquareRootResponse> getSquareRootMethod() {
    io.grpc.MethodDescriptor<com.proto.calculator.SquareRootRequest, com.proto.calculator.SquareRootResponse> getSquareRootMethod;
    if ((getSquareRootMethod = CalculatorServiceGrpc.getSquareRootMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getSquareRootMethod = CalculatorServiceGrpc.getSquareRootMethod) == null) {
          CalculatorServiceGrpc.getSquareRootMethod = getSquareRootMethod =
              io.grpc.MethodDescriptor.<com.proto.calculator.SquareRootRequest, com.proto.calculator.SquareRootResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SquareRoot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.SquareRootRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.calculator.SquareRootResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("SquareRoot"))
              .build();
        }
      }
    }
    return getSquareRootMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculatorServiceStub newStub(io.grpc.Channel channel) {
    return new CalculatorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CalculatorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalculatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CalculatorServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CalculatorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void calculator(com.proto.calculator.CalculatorRequest request,
        io.grpc.stub.StreamObserver<com.proto.calculator.CalculatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCalculatorMethod(), responseObserver);
    }

    /**
     */
    public void primeNumberDecomposition(com.proto.calculator.PrimeNumberDecompositionRequest request,
        io.grpc.stub.StreamObserver<com.proto.calculator.PrimeNumberDecompositionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPrimeNumberDecompositionMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.calculator.ComputeAverageRequest> computeAverage(
        io.grpc.stub.StreamObserver<com.proto.calculator.ComputeAverageResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getComputeAverageMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.calculator.FindMaxRequest> findMax(
        io.grpc.stub.StreamObserver<com.proto.calculator.FindMaxResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getFindMaxMethod(), responseObserver);
    }

    /**
     * <pre>
     *error handling
     *throws exception is number is negative
     * </pre>
     */
    public void squareRoot(com.proto.calculator.SquareRootRequest request,
        io.grpc.stub.StreamObserver<com.proto.calculator.SquareRootResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSquareRootMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCalculatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.proto.calculator.CalculatorRequest,
                com.proto.calculator.CalculatorResponse>(
                  this, METHODID_CALCULATOR)))
          .addMethod(
            getPrimeNumberDecompositionMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.proto.calculator.PrimeNumberDecompositionRequest,
                com.proto.calculator.PrimeNumberDecompositionResponse>(
                  this, METHODID_PRIME_NUMBER_DECOMPOSITION)))
          .addMethod(
            getComputeAverageMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.proto.calculator.ComputeAverageRequest,
                com.proto.calculator.ComputeAverageResponse>(
                  this, METHODID_COMPUTE_AVERAGE)))
          .addMethod(
            getFindMaxMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.proto.calculator.FindMaxRequest,
                com.proto.calculator.FindMaxResponse>(
                  this, METHODID_FIND_MAX)))
          .addMethod(
            getSquareRootMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.proto.calculator.SquareRootRequest,
                com.proto.calculator.SquareRootResponse>(
                  this, METHODID_SQUARE_ROOT)))
          .build();
    }
  }

  /**
   */
  public static final class CalculatorServiceStub extends io.grpc.stub.AbstractStub<CalculatorServiceStub> {
    private CalculatorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceStub(channel, callOptions);
    }

    /**
     */
    public void calculator(com.proto.calculator.CalculatorRequest request,
        io.grpc.stub.StreamObserver<com.proto.calculator.CalculatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCalculatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void primeNumberDecomposition(com.proto.calculator.PrimeNumberDecompositionRequest request,
        io.grpc.stub.StreamObserver<com.proto.calculator.PrimeNumberDecompositionResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getPrimeNumberDecompositionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.calculator.ComputeAverageRequest> computeAverage(
        io.grpc.stub.StreamObserver<com.proto.calculator.ComputeAverageResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getComputeAverageMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.calculator.FindMaxRequest> findMax(
        io.grpc.stub.StreamObserver<com.proto.calculator.FindMaxResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getFindMaxMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *error handling
     *throws exception is number is negative
     * </pre>
     */
    public void squareRoot(com.proto.calculator.SquareRootRequest request,
        io.grpc.stub.StreamObserver<com.proto.calculator.SquareRootResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSquareRootMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CalculatorServiceBlockingStub extends io.grpc.stub.AbstractStub<CalculatorServiceBlockingStub> {
    private CalculatorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.proto.calculator.CalculatorResponse calculator(com.proto.calculator.CalculatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getCalculatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.proto.calculator.PrimeNumberDecompositionResponse> primeNumberDecomposition(
        com.proto.calculator.PrimeNumberDecompositionRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getPrimeNumberDecompositionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *error handling
     *throws exception is number is negative
     * </pre>
     */
    public com.proto.calculator.SquareRootResponse squareRoot(com.proto.calculator.SquareRootRequest request) {
      return blockingUnaryCall(
          getChannel(), getSquareRootMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CalculatorServiceFutureStub extends io.grpc.stub.AbstractStub<CalculatorServiceFutureStub> {
    private CalculatorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.calculator.CalculatorResponse> calculator(
        com.proto.calculator.CalculatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCalculatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *error handling
     *throws exception is number is negative
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.calculator.SquareRootResponse> squareRoot(
        com.proto.calculator.SquareRootRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSquareRootMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALCULATOR = 0;
  private static final int METHODID_PRIME_NUMBER_DECOMPOSITION = 1;
  private static final int METHODID_SQUARE_ROOT = 2;
  private static final int METHODID_COMPUTE_AVERAGE = 3;
  private static final int METHODID_FIND_MAX = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CalculatorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CalculatorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALCULATOR:
          serviceImpl.calculator((com.proto.calculator.CalculatorRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.calculator.CalculatorResponse>) responseObserver);
          break;
        case METHODID_PRIME_NUMBER_DECOMPOSITION:
          serviceImpl.primeNumberDecomposition((com.proto.calculator.PrimeNumberDecompositionRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.calculator.PrimeNumberDecompositionResponse>) responseObserver);
          break;
        case METHODID_SQUARE_ROOT:
          serviceImpl.squareRoot((com.proto.calculator.SquareRootRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.calculator.SquareRootResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COMPUTE_AVERAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.computeAverage(
              (io.grpc.stub.StreamObserver<com.proto.calculator.ComputeAverageResponse>) responseObserver);
        case METHODID_FIND_MAX:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.findMax(
              (io.grpc.stub.StreamObserver<com.proto.calculator.FindMaxResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalculatorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.calculator.Calculator.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CalculatorService");
    }
  }

  private static final class CalculatorServiceFileDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier {
    CalculatorServiceFileDescriptorSupplier() {}
  }

  private static final class CalculatorServiceMethodDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CalculatorServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CalculatorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculatorServiceFileDescriptorSupplier())
              .addMethod(getCalculatorMethod())
              .addMethod(getPrimeNumberDecompositionMethod())
              .addMethod(getComputeAverageMethod())
              .addMethod(getFindMaxMethod())
              .addMethod(getSquareRootMethod())
              .build();
        }
      }
    }
    return result;
  }
}
