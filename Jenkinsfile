pipeline {
  agent any
  stages {
    stage('Stage1') {
      steps {
        sh 'echo \'compile\''
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