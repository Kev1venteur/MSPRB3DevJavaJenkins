pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                sh '''
                echo "Building JAR file with Maven"
                mvn clean package
                '''
            }
        }
        stage('execution') {
            steps {
                sh '''
                echo "Executing JAR file"
                mv target/msprb3-1.0.jar ./msprb3-1.0.jar
                java -jar msprb3-1.0.jar
                '''
            }
        }
    }
}