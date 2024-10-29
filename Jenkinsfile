pipeline {
    agent any

    tools {
        maven 'Maven-3.9.8'
        jdk 'JDK-17'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        git url: 'https://github.com/abijith-suresh/book-store-application.git', branch: 'main'
                    }
                }
            }
        }

        stage('Pre-Build') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        def services = ['api-gateway', 'config-service', 'registry-service', 'auth-service', 'author-service', 'book-service', 'gateway-service', 'postgresdb']

                        for (service in services) {
                            bat """
                            docker stop ${service} || true
                            docker rm ${service} || true
                            docker rmi -f ${service}:latest || true
                            docker network rm microservices-network || true
                            """
                        }
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    def services = ['api-gateway', 'config-service', 'registry-service', 'auth-service', 'author-service', 'book-service', 'gateway-service']

                    for (service in services) {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            dir(service) {
                                bat 'mvn clean package -DskipTests'
                            }
                        }
                    }
                }
            }
        }

        stage('Create Docker Network') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        bat 'docker network create microservices-network || echo "Network already exists"'
                    }
                }
            }
        }

        stage('Post-Build') {
            steps {
                script {
                    def services = ['api-gateway', 'config-service', 'registry-service', 'auth-service', 'author-service', 'book-service', 'gateway-service']

                    for (service in services) {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            dir(service) {
                                bat """
                                docker build -t ${service}:latest .
                                docker run -d --network microservices-network -p ${getPort(service)}:${getPort(service)} --name ${service} ${service}:latest
                                """
                            }
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed!'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline had errors, but execution continued.'
        }
    }
}

def getPort(service) {
    switch (service) {
        case 'api-gateway':
            return '9090'
        case 'config-service':
            return '8888'
        case 'registry-service':
            return '8761'
        case 'auth-service':
            return '9000'
        case 'author-service':
            return '9004'
        case 'book-service':
            return '9002'
        case 'gateway-service':
            return '9090' 
        default:
            return '8080' 
    }
}
