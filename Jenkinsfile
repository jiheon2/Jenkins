pipeline {
       agent any
//     agent {
//         kubernetes {
//             // 에이전트 Pod의 라벨 이름 지정
//             label 'dind-agent'
//             defaultContainer 'jnlp'
//             // YAML 형태로 Pod Template 정의
//             yaml """
// apiVersion: v1
// kind: Pod
// metadata:
//   labels:
//     role: jenkins-agent
// spec:
//   containers:
//   - name: jnlp
//     image: your-registry.example.com/jenkins-agent-dind:latest
//     imagePullPolicy: IfNotPresent
//     tty: true
//     command:
//     - cat
// """
//         }
//     }
    tools {
        // Global Tool Configuration에서 등록한 Maven 버전 사용
        maven 'Maven 3.9.9'
    }
    environment {
        // 애플리케이션 이름 및 Docker 이미지 태그 정보
        APP_NAME = "Jenkins"
        IMAGE_NAME = "Test-Image/${APP_NAME}"
        BUILD_TAG = "${env.BUILD_NUMBER}"
    }
    stages {
        stage('Checkout') {
            steps {
                // Git 저장소에서 소스코드 체크아웃
                checkout scm
            }
        }
        stage('Test') {
            steps {
                // Maven 단위 테스트 실행
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                // Maven 빌드 및 패키징 (테스트는 이미 실행했으므로 건너뛰기)
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    // 저장소 루트에 있는 Dockerfile을 사용해 Docker 이미지를 빌드
                    dockerImage = docker.build("${IMAGE_NAME}:${BUILD_TAG}")
                }
            }
        }
        stage('Post-Build') {
            steps {
                echo "Docker image built: ${IMAGE_NAME}:${BUILD_TAG}"
            }
        }
    }
    post {
        success {
            echo '빌드와 테스트가 성공적으로 완료되었습니다. 생성된 이미지를 확인하세요.'
        }
        failure {
            echo '빌드 또는 테스트에 실패하였습니다. 로그를 확인해주세용.'
        }
    }
}
