pipeline {
  agent any
  stages {
    stage('Stage1') {
      steps {
        sh 'make compile'
      }
    }

  }
  environment {
    TEST = 'True'
  }
}