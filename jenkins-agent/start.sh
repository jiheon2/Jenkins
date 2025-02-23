#!/bin/bash
# Docker 데몬을 백그라운드에서 시작합니다.
dockerd-entrypoint.sh &

# Docker 데몬이 기동될 때까지 최대 30초 대기합니다.
for i in {1..30}; do
    if docker info > /dev/null 2>&1; then
        echo "Docker daemon is up"
        break
    fi
    echo "Waiting for Docker daemon..."
    sleep 1
done

# Jenkins 에이전트 시작: 마스터에서 전달된 인자("$@")를 사용하여 연결합니다.
exec java -jar /home/jenkins/agent/agent.jar "$@"
