pipeline {
    agent any
    tools { maven 'Maven-3.9' }
    stages {
        stage('API tests') {
            steps {
                dir('backend-automation') {
                    sh 'mvn clean test -DbaseUrl=https://reqres.in'
                }
            }
        }
    }
    post {
        always {
            publishHTML(target: [reportDir: 'backend-automation/target/cucumber', reportFiles: 'cucumber.html', reportName: 'Cucumber Report'])
        }
    }
}
