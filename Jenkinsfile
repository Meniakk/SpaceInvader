pipeline {
  agent none
  stages {
    stage('Stage1') {
      steps {
        sh 'echo \'trying to make\''
        sh 'make compile'
      }
    }

    stage('stage2') {
      steps {
        sh 'echo stage2'
      }
    }

  }
}