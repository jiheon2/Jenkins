# 베이스 이미지: Docker DinD 기능을 제공하는 이미지 사용
FROM docker:20.10-dind

# Maven 빌드에 사용할 이미지 (Java 17 포함, slim 버전 사용)
FROM maven:3.8.6-openjdk-17-slim AS builder

# 작업 디렉터리 설정
WORKDIR /app

# 소스코드 복사 (pom.xml과 소스 파일)
COPY pom.xml .
# pom.xml만 먼저 복사하여 종속성 캐싱 활용
RUN mvn dependency:go-offline

# 소스 파일 전체 복사
COPY src ./src

# 애플리케이션 빌드 (JAR 파일 생성, 필요 시 테스트를 건너뛰려면 -DskipTests 옵션 추가)
RUN mvn clean package

# 2단계: 실행 단계 (Java 17)
FROM openjdk:17-jdk-slim

# 작업 디렉터리 생성
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일 복사 (생성된 JAR 파일명이 맞는지 확인)
COPY --from=builder /app/target/Jenkins-1.0-SNAPSHOT.jar ./app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

# -----------------------------------------------
# 아래는 커스텀 Jenkins 에이전트용 추가 이미지 구성 (DinD 및 Jenkins agent 기능)
# 이 부분은 애플리케이션 Dockerfile과는 별개로 관리합니다.
# -----------------------------------------------

# 커스텀 에이전트 이미지의 베이스는 다시 Docker DinD 이미지로 시작
FROM docker:20.10-dind AS jenkins-agent

USER root
RUN apk update && apk add --no-cache openjdk17-jre curl bash

# Jenkins 에이전트용 agent.jar 다운로드 (버전은 필요에 따라 조정)
RUN mkdir -p /home/jenkins/agent && \
    curl -L -o /home/jenkins/agent/agent.jar \
    https://repo.jenkins-ci.org/public/org/jenkins-ci/main/remoting/4.10/remoting-4.10.jar

# jenkins 사용자 생성 및 home 디렉터리 소유권 변경
RUN adduser -D jenkins && chown -R jenkins:jenkins /home/jenkins

WORKDIR /home/jenkins

# start.sh 스크립트를 printf를 사용해 생성
RUN printf '#!/bin/bash\n\
# Docker 데몬을 백그라운드에서 시작\n\
dockerd-entrypoint.sh &\n\
\n\
# Docker 데몬이 기동할 때까지 최대 30초 대기\n\
for i in {1..30}; do\n\
    if docker info > /dev/null 2>&1; then\n\
        echo "Docker daemon is up"\n\
        break\n\
    fi\n\
    echo "Waiting for Docker daemon..."\n\
    sleep 1\n\
done\n\
\n\
# Jenkins 에이전트 시작 (Jenkins 마스터에서 전달된 인자 사용)\n\
exec java -jar /home/jenkins/agent/agent.jar "$@"\n' > start.sh

RUN chmod +x start.sh

# 컨테이너 시작 시 start.sh 스크립트 실행 (커스텀 에이전트용)
ENTRYPOINT ["/home/jenkins/start.sh"]
