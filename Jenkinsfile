pipeline {
    agent none

    stages {

        stage('Build') {
            agent {
                docker {
                    image 'maven:3.9-eclipse-temurin-21'
                }
            }

            steps {
                sh 'mvn --version'

                sh 'mvn -B -Dmaven.repo.local=$WORKSPACE/.m2/repository clean package -DskipTests'

                stash name: 'app-jar', includes: 'target/*.jar'
            }
        }

        stage('Run') {
            agent any

            steps {
                unstash 'app-jar'

                sh '''
                    docker stop bookstore-api || true
                    docker rm bookstore-api || true

                    docker create \
                        --name bookstore-api \
                        -p 8081:9080 \
                        -v bookstore-data:/data \
                        -e DB_URL=jdbc:sqlite:/data/bookstore.db \
                        eclipse-temurin:21-jre \
                        java -jar /app.jar

                    JAR=$(ls target/*.jar | grep -v original | head -n 1)

                    docker cp "$JAR" bookstore-api:/app.jar

                    docker start bookstore-api
                '''
            }
        }
    }
}