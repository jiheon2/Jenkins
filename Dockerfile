# 1단계: 빌드 단계 (Maven 빌드 with Java 17)
FROM maven:3.8.6-openjdk-17 AS builder

# 작업 디렉토리 생성
WORKDIR /app

# 소스코드 복사 (pom.xml과 소스 파일)
COPY pom.xml .
# pom.xml만 먼저 복사해 종속성 캐싱 활용
RUN mvn dependency:go-offline

# 소스 파일 전체 복사
COPY src ./src

# 애플리케이션 빌드 (JAR 파일 생성)
RUN mvn clean package -DskipTests

# 2단계: 실행 단계 (Java 17)
FROM openjdk:17-jdk-slim

# 작업 디렉토리 생성
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일을 복사
COPY --from=builder /app/target/Jenkins-1.0-SNAPSHOT.jar ./app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
