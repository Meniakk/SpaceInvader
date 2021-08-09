pipeline {
  agent {
    kubernetes {
            defaultContainer 'myContainer'
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: myContainer
    image: truevolve/ubuntu-java-wine:latest
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
