pipeline {
 agent { label 'docker-java17' }
 options { skipDefaultCheckout(true); disableConcurrentBuilds(); timestamps() }
 triggers { pollSCM('H/5 * * * *') }
 environment { IMAGE = 'pan-eduard-student-api:v1.0-pan-eduard' }
 stages {
  stage('Checkout') { steps { checkout scm; echo 'Pan Eduard | IT2-2312' } }
  stage('Build') { steps { sh 'chmod +x gradlew && ./gradlew --no-daemon classes bootJar' } }
  stage('Test') { steps { sh './gradlew --no-daemon test' } post { always { junit 'build/test-results/test/*.xml' } } }
  stage('Docker Build') { steps { sh 'docker build -t "$IMAGE" .' } }
  stage('Verify') { steps { sh 'docker image inspect "$IMAGE"' } }
 }
 post { success { archiveArtifacts artifacts: 'build/libs/app.jar', fingerprint: true } }
}
