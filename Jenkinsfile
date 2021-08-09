pipeline {
  agent {
    kubernetes {
            defaultContainer 'mycontainer'
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: mycontainer
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
