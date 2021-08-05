pipeline {
  agent any
  stages {
    stage('Stage1') {
      steps {
        sh '''#!/bin/bash -l
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