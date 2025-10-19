#!/bin/bash

# 创建目录
mkdir -p /home/ljl/grpc-project
cd /home/ljl/grpc-project

# 创建项目结构
mkdir -p src/main/java/com/example/grpc
mkdir -p src/main/proto

# 创建proto文件
cat > src/main/proto/grpc_service.proto <<EOF
syntax = "proto3";

package grpc_service;

service GrpcService {
  rpc GeneratePin (PinRequest) returns (PinResponse);
  rpc GenerateJwt (JwtRequest) returns (JwtResponse);
}

message PinRequest {
  int32 length = 1;
}

message PinResponse {
  string pin = 1;
}

message JwtRequest {
  string subject = 1;
  int64 expirationTime = 2;
}

message JwtResponse {
  string jwtToken = 1;
}
EOF

# 创建服务端Java代码
cat > src/main/java/com/example/grpc/GrpcServer.java <<EOF
package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.example.grpc.grpc_service.*;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;

public class GrpcServer {
    private Server server;

    private void start() throws Exception {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new GrpcServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        server.awaitTermination();
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        final GrpcServer server = new GrpcServer();
        server.start();
    }

    static class GrpcServiceImpl extends GrpcServiceGrpc.GrpcServiceImplBase {
        @Override
        public void generatePin(PinRequest request, StreamObserver<PinResponse> responseObserver) {
            String pin = generatePinCode(request.getLength());
            PinResponse response = PinResponse.newBuilder().setPin(pin).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void generateJwt(JwtRequest request, StreamObserver<JwtResponse> responseObserver) {
            String jwtToken = generateJwtToken(request.getSubject(), request.getExpirationTime());
            JwtResponse response = JwtResponse.newBuilder().setJwtToken(jwtToken).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        private String generatePinCode(int length) {
            Random random = new Random();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(random.nextInt(10));
            }
            return sb.toString();
        }

        private String generateJwtToken(String subject, long expirationTime) {
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("secretKey".getBytes()))
                    .compact();
        }
    }
}
EOF

# 创建客户端Java代码
cat > src/main/java/com/example/grpc/GrpcClient.java <<EOF
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

    public static void main(String[] args) throws Exception {
        GrpcClient client = new GrpcClient("localhost", 50051);
        try {
            System.out.println("Generated PIN: " + client.generatePin(6));
            System.out.println("Generated JWT: " + client.generateJwt("testUser", 3600000));
        } finally {
            client.shutdown();
        }
    }
}
EOF

# 创建build.gradle文件
cat > build.gradle <<EOF
plugins {
    id 'java'
    id 'com.google.protobuf' version '0.8.17'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.grpc:grpc-netty-shaded:1.43.2'
    implementation 'io.grpc:grpc-protobuf:1.43.2'
    implementation 'io.grpc:grpc-stub:1.43.2'
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
    testImplementation 'junit:junit:4.13.2'
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.17.3"
    }
    generateProtoTasks {
        all().each { task ->
            task.builtins {
                java {}
            }
        }
    }
}

compileJava {
    options.compilerArgs += ['-parameters']
}
EOF

# 下载protobuf插件
echo "Downloading protobuf plugin..."
curl -L https://github.com/grpc/grpc-java/releases/download/v1.43.2/protoc-gen-grpc-java-1.43.2-linux-x86_64.exe -o /home/ljl/grpc-project/protoc-gen-grpc-java
chmod +x /home/ljl/grpc-project/protoc-gen-grpc-java

# 设置环境变量
export PATH=$PATH:/home/ljl/grpc-project

# 生成Java代码
echo "Generating Java code from proto file..."
protoc --proto_path=src/main/proto --java_out=src/main/java src/main/proto/grpc_service.proto --plugin=protoc-gen-grpc-java=/home/ljl/grpc-project/protoc-gen-grpc-java

# 清理protobuf插件
rm /home/ljl/grpc-project/protoc-gen-grpc-java

echo "项目设置完成！你可以通过gradle命令来构建项目了。"
