package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.example.grpc.grpc_service.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.security.KeyStore;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import java.util.Random;
import java.util.Date;
import java.util.Base64;

public class GrpcServer {
    private Server server;
    private final JwtTokenProvider jwtTokenProvider;

    public GrpcServer() throws Exception {
        this.jwtTokenProvider = new JwtTokenProvider();
    }

    private void start() throws Exception {
        int port = 8443;

        // SSL配置 - 使用Netty的SslContext
        File serverCertChainFile = new File("certs/server.crt");
        File serverPrivateKeyFile = new File("certs/server.key");
        File clientCaCertChainFile = new File("certs/client.crt");

        // 使用Netty的SslContext而不是javax.net.ssl.SSLContext
        SslContext sslContext = GrpcSslContexts.forServer(serverCertChainFile, serverPrivateKeyFile)
                .trustManager(clientCaCertChainFile)
                .build();

        server = NettyServerBuilder.forPort(port)
                .addService(new GrpcServiceImpl())
                .sslContext(sslContext)
                .build()
                .start();
        

        System.out.println("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            GrpcServer.this.stop();
            System.err.println("*** server shut down");
        }));

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
        private final JwtTokenProvider jwtTokenProvider;
        
        public GrpcServiceImpl() throws Exception {
            this.jwtTokenProvider = new JwtTokenProvider();
        }

        @Override
        public void generatePin(PinRequest request, StreamObserver<PinResponse> responseObserver) {
            try {
                String pin = generatePinCode(request.getLength());
                PinResponse response = PinResponse.newBuilder().setPin(pin).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } catch (Exception e) {
                responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("生成PIN码失败").asRuntimeException());
            }
        }

        @Override
        public void generateJwt(JwtRequest request, StreamObserver<JwtResponse> responseObserver) {
            try {
                String jwtToken = jwtTokenProvider.generateToken(request.getSubject(), request.getExpirationTime());
                JwtResponse response = JwtResponse.newBuilder().setJwtToken(jwtToken).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } catch (Exception e) {
                responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("生成JWT令牌失败").asRuntimeException());
            }
        }

        @Override
        public void validateJwt(ValidateRequest request, StreamObserver<ValidateResponse> responseObserver) {
            try {
                JwtValidationResult validationResult = jwtTokenProvider.validateToken(request.getJwtToken());
                
                ValidateResponse.Builder responseBuilder = ValidateResponse.newBuilder()
                        .setIsValid(validationResult.isValid())
                        .setSubject(validationResult.getSubject() != null ? validationResult.getSubject() : "")
                        .setExpirationTime(validationResult.getExpirationTime() != null ? validationResult.getExpirationTime().getTime() : 0);
                
                if (validationResult.getErrorMessage() != null) {
                    responseBuilder.setErrorMessage(validationResult.getErrorMessage());
                }
                
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
            } catch (Exception e) {
                responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("验证JWT令牌失败").asRuntimeException());
            }
        }

        private String generatePinCode(int length) {
            if (length <= 0 || length > 20) {
                throw new IllegalArgumentException("PIN长度必须在1-20之间");
            }
            Random random = new Random();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(random.nextInt(10));
            }
            return sb.toString();
        }
    }
    
    // JWT令牌提供者类
    static class JwtTokenProvider {
        private final SecretKey secretKey;
        private final JwtParser jwtParser;
        
        public JwtTokenProvider() throws Exception {
            this.secretKey = loadKeyFromKeyStore();
            
            this.jwtParser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();
        }
        
        // 从KeyStore加载密钥
        private SecretKey loadKeyFromKeyStore() throws Exception {
            String keystorePath = System.getProperty("keystore.path", "keystore.jks");
            String keystorePassword = System.getProperty("keystore.password", "changeit");
            String keyAlias = System.getProperty("keystore.alias", "jwtkey");
            String keyPassword = System.getProperty("keystore.keypassword", "changeit");
            
            try (InputStream is = Files.newInputStream(Paths.get(keystorePath))) {
                KeyStore keyStore = KeyStore.getInstance("JKS");
                keyStore.load(is, keystorePassword.toCharArray());
                
                KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(
                    keyAlias, new KeyStore.PasswordProtection(keyPassword.toCharArray()));
                
                return secretKeyEntry.getSecretKey();
            } catch (Exception e) {
                System.err.println("无法从KeyStore加载密钥，将生成新密钥: " + e.getMessage());
                // 开发环境下生成新密钥
                return Keys.secretKeyFor(SignatureAlgorithm.HS256);
            }
        }
        
        // 从环境变量加载密钥
        private SecretKey loadKeyFromEnv() {
            String base64Key = System.getenv("JWT_SECRET_KEY");
            if (base64Key == null || base64Key.trim().isEmpty()) {
                throw new IllegalStateException("JWT_SECRET_KEY环境变量未设置");
            }
            byte[] keyBytes = Base64.getDecoder().decode(base64Key);
            return Keys.hmacShaKeyFor(keyBytes);
        }
        
        public String generateToken(String subject, long expirationTime) {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expirationTime);
            
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        }
        
        public JwtValidationResult validateToken(String token) {
            try {
                Claims claims = jwtParser.parseClaimsJws(token).getBody();
                
                return new JwtValidationResult(
                    true,
                    claims.getSubject(),
                    claims.getExpiration(),
                    null
                );
            } catch (ExpiredJwtException e) {
                return new JwtValidationResult(false, null, null, "令牌已过期");
            } catch (UnsupportedJwtException e) {
                return new JwtValidationResult(false, null, null, "不支持的令牌格式");
            } catch (MalformedJwtException e) {
                return new JwtValidationResult(false, null, null, "令牌格式错误");
            } catch (SecurityException e) {
                // 使用SecurityException替代已弃用的SignatureException
                return new JwtValidationResult(false, null, null, "令牌签名无效");
            } catch (Exception e) {
                return new JwtValidationResult(false, null, null, "令牌验证失败: " + e.getMessage());
            }
        }
    }
    
    // JWT验证结果类
    static class JwtValidationResult {
        private final boolean valid;
        private final String subject;
        private final Date expirationTime;
        private final String errorMessage;
        
        public JwtValidationResult(boolean valid, String subject, Date expirationTime, String errorMessage) {
            this.valid = valid;
            this.subject = subject;
            this.expirationTime = expirationTime;
            this.errorMessage = errorMessage;
        }
        
        public boolean isValid() { return valid; }
        public String getSubject() { return subject; }
        public Date getExpirationTime() { return expirationTime; }
        public String getErrorMessage() { return errorMessage; }
    }
}