pipeline {
  agent any
  stages {
    stage('Stage1') {
      steps {
        sh '''echo \'Trying to install make...\'
apt-get install build-essential
echo \'building...\'
make compile'''
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