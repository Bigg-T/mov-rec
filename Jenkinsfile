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
                sh 'mvn comile -f team-31-spring18/pom.xml  compile'
                sh 'mvn package -f team-31-spring18/pom.xml  package'
                sh 'mvn compile'
                sh 'mvn package'

              
            }
        }
}

        stage('Test'){
            steps {
                echo "Testing"
                sh 'mvn test -f team-31-spring18/pom.xml  test'
                echo "Testing"
                sh 'mvn test'

            }
}

	 stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                        sh 'mvn clean install'
                        sh 'mvn sonar:sonar'
                }
            }
        }
            
        stage('Quality') {
            steps {
                   script {
                         timeout(time: 1, unit: 'HOURS') {
                            def qg = waitForQualityGate()
                            if (qg.status != 'OK') {
                                error "Pipeline aborted due to quality gate failure: ${qg.status}"
                            }

        }
    }
}
                       }
                   }
