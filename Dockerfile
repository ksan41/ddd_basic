# 1단계: Maven이 포함된 JDK 이미지를 사용하여 빌드
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# 2단계: 경량화된 JDK 이미지를 사용하여 실행
FROM mcr.microsoft.com/openjdk/jdk:17-distroless
WORKDIR /app

# wait-for-it.sh 파일 복사
COPY wait-for-it.sh /wait-for-it.sh

# 빌드된 JAR 복사
COPY --from=builder /app/target/*.jar app.jar

# 실행 시 wait-for-it.sh를 활용하도록 설정
ENTRYPOINT ["/wait-for-it.sh", "mysql", "3306", "--", "java", "-jar", "app.jar"]