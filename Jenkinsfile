pipeline {
  agent {
    kubernetes {
            defaultContainer 'jdk'
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: make
    image: kitware/cmake:ci-hip4.2-x86_64-2021-07-09
    command:
    - cat
    tty: true
  - name: jdk
    image: openjdk:8
    command:
    - cat
    tty: true
"""
        }

  }
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
