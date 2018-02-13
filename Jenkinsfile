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
              avadhera-patch-3-1
                sh 'mvn -f team-31-spring18/pom.xml  compile'
                sh 'mvn -f team-31-spring18/pom.xml  package'

               
  spring2
            }
        }
        stage('Test'){
            steps {
                echo "Testing"
              avadhera-patch-3-1
                sh 'mvn -f team-31-spring18/pom.xml  test'

spring2
            }
        }
    }
}
