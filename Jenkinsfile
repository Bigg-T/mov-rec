pipeline {
     agent {
       docker {
           image 'maven:3-alpine'
           args '-v /root/.m2:/root/.m2'
       }
   }


    stages {
        stage('Build') {
            steps {
                echo "Building"
                sh 'mvn -f project/pom.xml  compile'
                sh 'mvn -f project/pom.xml  package'
            }
        }
        stage('Test'){
            steps {
                echo "Testing"
                sh 'mvn -f project/pom.xml  test'
            }
        }
    }
}
