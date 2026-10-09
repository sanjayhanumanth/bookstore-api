
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run') {
            steps {
                sh '''
                    docker stop bookstore-api || true
                    docker rm bookstore-api || true

                    docker build -t bookstore-api .

                    docker run -d \
                        --name bookstore-api \
                        -p 8081:9080 \
                        bookstore-api
                '''
            }
        }
    }
}
