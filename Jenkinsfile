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
                sh 'mvn compile -f team-31-spring18/pom.xml  compile'
                sh 'mvn package -f team-31-spring18/pom.xml  package'

              
            }
        }
        stage('Test'){
            steps {
                echo "Testing"
                sh 'mvn test -f team-31-spring18/pom.xml  test'

            }
        }
    }
}
