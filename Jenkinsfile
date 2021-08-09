pipeline {
  agent {
    docker {
      image 'kitware/cmake'
    }

  }
  stages {
    stage('Stage1') {
      steps {
        sh 'echo \'trying to make\''
        sh 'dockerd'
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