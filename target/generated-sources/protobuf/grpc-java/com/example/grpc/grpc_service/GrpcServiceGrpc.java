package com.example.grpc.grpc_service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.2)",
    comments = "Source: grpc_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GrpcServiceGrpc {

  private GrpcServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc_service.GrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.grpc_service.PinRequest,
      com.example.grpc.grpc_service.PinResponse> getGeneratePinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GeneratePin",
      requestType = com.example.grpc.grpc_service.PinRequest.class,
      responseType = com.example.grpc.grpc_service.PinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.grpc_service.PinRequest,
      com.example.grpc.grpc_service.PinResponse> getGeneratePinMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.grpc_service.PinRequest, com.example.grpc.grpc_service.PinResponse> getGeneratePinMethod;
    if ((getGeneratePinMethod = GrpcServiceGrpc.getGeneratePinMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getGeneratePinMethod = GrpcServiceGrpc.getGeneratePinMethod) == null) {
          GrpcServiceGrpc.getGeneratePinMethod = getGeneratePinMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.grpc_service.PinRequest, com.example.grpc.grpc_service.PinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GeneratePin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.grpc_service.PinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.grpc_service.PinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("GeneratePin"))
              .build();
        }
      }
    }
    return getGeneratePinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.grpc_service.JwtRequest,
      com.example.grpc.grpc_service.JwtResponse> getGenerateJwtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GenerateJwt",
      requestType = com.example.grpc.grpc_service.JwtRequest.class,
      responseType = com.example.grpc.grpc_service.JwtResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.grpc_service.JwtRequest,
      com.example.grpc.grpc_service.JwtResponse> getGenerateJwtMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.grpc_service.JwtRequest, com.example.grpc.grpc_service.JwtResponse> getGenerateJwtMethod;
    if ((getGenerateJwtMethod = GrpcServiceGrpc.getGenerateJwtMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getGenerateJwtMethod = GrpcServiceGrpc.getGenerateJwtMethod) == null) {
          GrpcServiceGrpc.getGenerateJwtMethod = getGenerateJwtMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.grpc_service.JwtRequest, com.example.grpc.grpc_service.JwtResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GenerateJwt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.grpc_service.JwtRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.grpc_service.JwtResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("GenerateJwt"))
              .build();
        }
      }
    }
    return getGenerateJwtMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.grpc_service.ValidateRequest,
      com.example.grpc.grpc_service.ValidateResponse> getValidateJwtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidateJwt",
      requestType = com.example.grpc.grpc_service.ValidateRequest.class,
      responseType = com.example.grpc.grpc_service.ValidateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.grpc_service.ValidateRequest,
      com.example.grpc.grpc_service.ValidateResponse> getValidateJwtMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.grpc_service.ValidateRequest, com.example.grpc.grpc_service.ValidateResponse> getValidateJwtMethod;
    if ((getValidateJwtMethod = GrpcServiceGrpc.getValidateJwtMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getValidateJwtMethod = GrpcServiceGrpc.getValidateJwtMethod) == null) {
          GrpcServiceGrpc.getValidateJwtMethod = getValidateJwtMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.grpc_service.ValidateRequest, com.example.grpc.grpc_service.ValidateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidateJwt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.grpc_service.ValidateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.grpc_service.ValidateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("ValidateJwt"))
              .build();
        }
      }
    }
    return getValidateJwtMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceStub>() {
        @java.lang.Override
        public GrpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceStub(channel, callOptions);
        }
      };
    return GrpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceBlockingStub>() {
        @java.lang.Override
        public GrpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceBlockingStub(channel, callOptions);
        }
      };
    return GrpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceFutureStub>() {
        @java.lang.Override
        public GrpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceFutureStub(channel, callOptions);
        }
      };
    return GrpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GrpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void generatePin(com.example.grpc.grpc_service.PinRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.PinResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGeneratePinMethod(), responseObserver);
    }

    /**
     */
    public void generateJwt(com.example.grpc.grpc_service.JwtRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.JwtResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGenerateJwtMethod(), responseObserver);
    }

    /**
     */
    public void validateJwt(com.example.grpc.grpc_service.ValidateRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.ValidateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidateJwtMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGeneratePinMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.grpc_service.PinRequest,
                com.example.grpc.grpc_service.PinResponse>(
                  this, METHODID_GENERATE_PIN)))
          .addMethod(
            getGenerateJwtMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.grpc_service.JwtRequest,
                com.example.grpc.grpc_service.JwtResponse>(
                  this, METHODID_GENERATE_JWT)))
          .addMethod(
            getValidateJwtMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.grpc_service.ValidateRequest,
                com.example.grpc.grpc_service.ValidateResponse>(
                  this, METHODID_VALIDATE_JWT)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcServiceStub extends io.grpc.stub.AbstractAsyncStub<GrpcServiceStub> {
    private GrpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void generatePin(com.example.grpc.grpc_service.PinRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.PinResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGeneratePinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void generateJwt(com.example.grpc.grpc_service.JwtRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.JwtResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGenerateJwtMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void validateJwt(com.example.grpc.grpc_service.ValidateRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.ValidateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidateJwtMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GrpcServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<GrpcServiceBlockingStub> {
    private GrpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.grpc_service.PinResponse generatePin(com.example.grpc.grpc_service.PinRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGeneratePinMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.grpc_service.JwtResponse generateJwt(com.example.grpc.grpc_service.JwtRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGenerateJwtMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.grpc_service.ValidateResponse validateJwt(com.example.grpc.grpc_service.ValidateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidateJwtMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcServiceFutureStub extends io.grpc.stub.AbstractFutureStub<GrpcServiceFutureStub> {
    private GrpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.grpc_service.PinResponse> generatePin(
        com.example.grpc.grpc_service.PinRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGeneratePinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.grpc_service.JwtResponse> generateJwt(
        com.example.grpc.grpc_service.JwtRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGenerateJwtMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.grpc_service.ValidateResponse> validateJwt(
        com.example.grpc.grpc_service.ValidateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidateJwtMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GENERATE_PIN = 0;
  private static final int METHODID_GENERATE_JWT = 1;
  private static final int METHODID_VALIDATE_JWT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GENERATE_PIN:
          serviceImpl.generatePin((com.example.grpc.grpc_service.PinRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.PinResponse>) responseObserver);
          break;
        case METHODID_GENERATE_JWT:
          serviceImpl.generateJwt((com.example.grpc.grpc_service.JwtRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.JwtResponse>) responseObserver);
          break;
        case METHODID_VALIDATE_JWT:
          serviceImpl.validateJwt((com.example.grpc.grpc_service.ValidateRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.grpc_service.ValidateResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.grpc_service.GrpcServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcService");
    }
  }

  private static final class GrpcServiceFileDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier {
    GrpcServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcServiceMethodDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcServiceFileDescriptorSupplier())
              .addMethod(getGeneratePinMethod())
              .addMethod(getGenerateJwtMethod())
              .addMethod(getValidateJwtMethod())
              .build();
        }
      }
    }
    return result;
  }
}
