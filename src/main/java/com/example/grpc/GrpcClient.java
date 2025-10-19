package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.grpc.grpc_service.*;

import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class GrpcClient {
    private final ManagedChannel channel;
    private final GrpcServiceGrpc.GrpcServiceBlockingStub blockingStub;

    public GrpcClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.blockingStub = GrpcServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String generatePin(int length) {
        PinRequest request = PinRequest.newBuilder().setLength(length).build();
        PinResponse response = blockingStub.generatePin(request);
        return response.getPin();
    }

    public String generateJwt(String subject, long expirationTime) {
        JwtRequest request = JwtRequest.newBuilder().setSubject(subject).setExpirationTime(expirationTime).build();
        JwtResponse response = blockingStub.generateJwt(request);
        return response.getJwtToken();
    }
    
    public ValidateResponse validateJwt(String jwtToken) {
        ValidateRequest request = ValidateRequest.newBuilder().setJwtToken(jwtToken).build();
        return blockingStub.validateJwt(request);
    }

    public static void main(String[] args) throws Exception {
        GrpcClient client = new GrpcClient("localhost", 50051);
        try {
            Scanner scanner = new Scanner(System.in);
            
            // 生成PIN
            System.out.println("Generated 6-digit PIN: " + client.generatePin(6));
            
            // 生成JWT
            String jwtToken = client.generateJwt("testUser", 3600000); // 1小时有效期
            System.out.println("Generated JWT: " + jwtToken);
            
            // 验证JWT
            ValidateResponse validation = client.validateJwt(jwtToken);
            System.out.println("\nJWT验证结果:");
            System.out.println("是否有效: " + validation.getIsValid());
            System.out.println("用户主体: " + validation.getSubject());
            System.out.println("过期时间: " + new java.util.Date(validation.getExpirationTime()));
            
            if (!validation.getErrorMessage().isEmpty()) {
                System.out.println("错误信息: " + validation.getErrorMessage());
            }
            
            // 测试无效令牌
            System.out.println("\n测试无效令牌验证:");
            ValidateResponse invalidValidation = client.validateJwt("invalid.token.here");
            System.out.println("是否有效: " + invalidValidation.getIsValid());
            System.out.println("错误信息: " + invalidValidation.getErrorMessage());
            
        } finally {
            client.shutdown();
        }
    }
}