pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS') // Timeout the entire pipeline after 1 hour
        retry(1) // Retry failed stages up to 3 times
        buildDiscarder(logRotator(numToKeepStr: '10')) // Keep the last 10 builds
        skipDefaultCheckout() // Skip the default checkout
        timestamps()//add timestamp in the log
        disableConcurrentBuilds()// at a time, only one build should run

    }
    tools {
                jdk 'JDK 1.17' // Use JDK 17 installed on the agent(jenkins server)
                maven 'M2_HOME' // Use gradle gitinstalled on the agent(jenkins server)
        }
    environment {
        CONFIG_FILE = 'sdp.yml'
        }
      stages {
         stage('Initialize') {
            steps {
                script {
                    checkout scm
                    // Read the properties file
                    def props  = readYaml file: "${CONFIG_FILE}"
                    env.VERSION = props.version
                    env.ON_SUCCESS_EMAIL = props.onSuccessEmail
                    env.ON_FAILURE_EMAIL = props.onFailureEmail
                    }
                }
             }
             stage('Build') {
                steps {
                    script {
                        echo "Building version ${env.VERSION}"
                        sh 'mvn clean package'
                        }
                }
             }
       }
 }