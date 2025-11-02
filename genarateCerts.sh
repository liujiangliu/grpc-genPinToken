#!/bin/bash

# 确保脚本在项目根目录下运行
PROJECT_ROOT=$(dirname "$0")
CERTS_DIR="$PROJECT_ROOT/certs"

# 创建证书目录（如果不存在）
mkdir -p "$CERTS_DIR"

# 生成客户端密钥对
openssl genpkey -algorithm RSA -out "$CERTS_DIR/client.key" -pkeyopt rsa_keygen_bits:2048

# 生成客户端证书签名请求
openssl req -new -key "$CERTS_DIR/client.key" -out "$CERTS_DIR/client.csr" -subj "/CN=localhost"

# 生成自签名客户端证书
openssl x509 -req -days 365 -in "$CERTS_DIR/client.csr" -signkey "$CERTS_DIR/client.key" -out "$CERTS_DIR/client.crt"

# 生成服务端密钥对
openssl genpkey -algorithm RSA -out "$CERTS_DIR/server.key" -pkeyopt rsa_keygen_bits:2048

# 生成服务端证书签名请求
openssl req -new -key "$CERTS_DIR/server.key" -out "$CERTS_DIR/server.csr" -subj "/CN=localhost"

# 生成自签名服务端证书
openssl x509 -req -days 365 -in "$CERTS_DIR/server.csr" -signkey "$CERTS_DIR/server.key" -out "$CERTS_DIR/server.crt"

# 清理中间文件
rm "$CERTS_DIR/client.csr" "$CERTS_DIR/server.csr"

echo "Certificates generated in $CERTS_DIR"
