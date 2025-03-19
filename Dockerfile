# 1단계: 빌드 단계
FROM maven:3.8.7-openjdk-17 AS build

# 작업 디렉터리 설정
WORKDIR /app

# Maven 캐싱을 위해 pom.xml만 먼저 복사
COPY pom.xml .
RUN mvn dependency:go-offline

# 나머지 소스 코드 복사
COPY src ./src

# 애플리케이션 빌드 (필요 시 -DskipTests 옵션을 추가할 수 있습니다)
RUN mvn clean package

# 2단계: 실행 단계
FROM openjdk:17-jdk-slim

# 작업 디렉터리 설정
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일 복사 (JAR 파일 이름은 실제 프로젝트에 맞게 변경)
COPY --from=build /app/target/your-app.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
