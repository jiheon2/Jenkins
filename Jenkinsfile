pipeline {
    agent any
    tools {
        // 위에서 등록한 Maven 이름
        maven 'Maven 3.9.9'
    }
    environment {
            // 애플리케이션 이름 및 Docker 이미지 태그 정보
            APP_NAME = "Jenkins"
            IMAGE_NAME = "Test-Image/${APP_NAME}"
            // 여기서는 Harbor에 푸시하지 않으므로, 레지스트리 URL은 이미지 태그 용도로만 사용
            BUILD_TAG = "${env.BUILD_NUMBER}"
        }
    stages {
        stage('Checkout') {
            steps {
                // Git 저장소에서 소스코드 체크아웃
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Maven 빌드 및 패키징
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                   // Docker 이미지를 빌드합니다.
                   // Dockerfile은 저장소 루트에 있다고 가정합니다.
                   dockerImage = docker.build("${IMAGE_NAME}:${BUILD_TAG}")
                }
            }
        }
        stage('Test') {
            steps {
                // Maven 단위 테스트 실행
                sh 'mvn test'
            }
        }
        stage('Post-Build') {
            steps {
                // 빌드된 이미지 정보를 출력합니다.
                echo "Docker image built: ${IMAGE_NAME}:${BUILD_TAG}"
            }
        }
    }
    post {
        success {
            echo '빌드와 테스트가 성공적으로 끝났습니다. 이미지가 생성되었습니다. 이미지를 확인하세요.'
        }
        failure {
            echo '빌드 혹은 테스트가 실패하였습니다. 로그를 확인해주세용.'
        }
    }
}
