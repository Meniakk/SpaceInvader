pipeline {
  agent any
  stages {
    stage('Stage1') {
      steps {
        sh 'make build'
      }
    }

    stage('stage2') {
      steps {
        sh 'echo stage2'
      }
    }

  }
  environment {
    TEST = 'True'
  }
}