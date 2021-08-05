pipeline {
  agent any
  stages {
    stage('Stage1') {
      steps {
        sh 'echo \'trying to make\''
        sh 'make compile'
        catchError(catchInterruptions: true, buildResult: 'Fail', stageResult: 'Fail', message: 'Failed') {
          sh 'echo \'why god\''
        }

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