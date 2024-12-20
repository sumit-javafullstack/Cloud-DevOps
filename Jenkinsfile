pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS') // Timeout the entire pipeline after 1 hour
        retry(1) // Retry failed stages up to 3 times
        buildDiscarder(logRotator(numToKeepStr: '10')) // Keep the last 10 builds
        skipDefaultCheckout() // Skip the default checkout
        //timestamps()//add timestamp in the log
        disableConcurrentBuilds()// at a time, only one build should run

    }
    tools {
                jdk 'JDK 1.17' // Use JDK 17 installed on the agent(jenkins server)
                maven 'M2_HOME' // Use gradle gitinstalled on the agent(jenkins server)
        }
    environment {
        CONFIG_FILE = 'sdp.yml'
        TOMCAT_URL = 'http://54.91.136.208:8080/' // Replace with your actual Tomcat manager URL
        }

      stages {
         stage('Initialize') {
            steps {
                script {
                    checkout scm
                    // Read the properties file
                    def props  = readYaml file: "${CONFIG_FILE}"
                    env.VERSION = props.version
                    env.ARTIFACTORY_SERVER = props.artifactoryServer
                    env.ARTIFACTORY_CREDENTIALS = props.artifactoryCredentialId
                    env.REPO = props.artifactoryRepo
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
// we will use pipeline to deploy to different environment
//              stage('Deploy to Tomcat') {
//                  steps {
//                       echo 'Deploying to Tomcat...'
//                           withCredentials([usernamePassword(credentialsId: 'tomcat', usernameVariable: 'TOMCAT_USER', passwordVariable: 'TOMCAT_PASSWORD')]) {
//                           sh """
//                            curl -u $TOMCAT_USER:$TOMCAT_PASSWORD \
//                            --upload-file target/*.jar \
//                            $TOMCAT_URL
//                              """
//                            }
//                       }
//                  }



             stage('Upload to Artifactory') {
                         steps {
                             script {
                             //"${env.ARTIFACTORY_SERVER}"
                                 def artifactoryServer = Artifactory.newServer url: 'https://sumit06420.jfrog.io/artifactory', credentialsId: 'artifactory'
                                 def uploadSpec = """{
                                     "files": [
                                         {
                                             "pattern": "target/*.jar",
                                             "target": "${env.REPO}/"
                                         }
                                     ]
                                 }"""
                                 artifactoryServer.upload spec: uploadSpec
                             }
                         }
                     }
       }

 }