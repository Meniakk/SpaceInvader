pipeline {
  agent {
    kubernetes {
            defaultContainer 'jdk'
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: jdk
    image: kitware/cmake
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
