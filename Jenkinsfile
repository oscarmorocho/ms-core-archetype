def IMAGEN
def APP_VERSION
def jenkinsWorker = 'jenkins-worker'

def nodeLabel = 'jenkins-job'
pipeline {
  agent {
    kubernetes {
      cloud 'openshift'
      label nodeLabel
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    identifier: ${nodeLabel}
spec:
  serviceAccountName: jenkins
  containers:
  - name: tools
    image: 331022218908.dkr.ecr.us-east-1.amazonaws.com/tools:1.0.0 # Clients: aws oc klar 
    command:
    - cat
    tty: true
"""
    }
  }
    environment {
        AWS_ACCESS_KEY_ID     = credentials('jenkins-aws-secret-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('jenkins-aws-secret-access-key')
        AWS_REGION = "us-east-1"
        APP_NAME = ""
        APP_VERSION = ""
        IMAGE = ""
        REGISTRY = "331022218908.dkr.ecr.us-east-1.amazonaws.com"
        REPOSITORY = "apiservice"
        PUSH = ""
        NAMESPACE = ""
        URL_OPENSHIFT = "https://api.dinersclub-dev.b6r7.p1.openshiftapps.com:6443"
    }
    options {
        timestamps ()
        timeout(time: 15, unit: 'MINUTES')
    }
    stages {
        stage('Stage: Versioning') {
            agent any
            steps {
                script {
                    def artifact = readMavenPom().getArtifactId()
                    IMAGEN = readMavenPom().getArtifactId()
                    echo "Nombre del Artefacto Docker: ${IMAGEN}"
                    
                    //REPOSITORY = "${REPOSITORY}/${artifact}"
                    echo "Nombre del Repositorio ECR: ${REPOSITORY}"
                    
                    PUSH = "${REGISTRY}/${REPOSITORY}"
                    echo "Push del Repositorio ECR: ${PUSH}"
                    
                    APP_NAME = readMavenPom().getArtifactId()
                    echo "Nombre del Artefacto Openshift: ${APP_NAME}"
                    
                    APP_VERSION = readMavenPom().getVersion()
                    echo "Version actual: ${APP_VERSION}"
                }
            }
        }
        stage('Stage: Environment') {
            agent any
            steps {
                script {
                    def branch = "${env.BRANCH_NAME}"
                    echo " --> Rama: ${branch}"
                    
                    switch(branch) {
                    case 'develop': 
                        AMBIENTE = 'dev'
                        NAMESPACE = 'apiservice-microservicios'
                        break
                    case 'semantic-release/patch': 
                        AMBIENTE = 'rc'
                        break
                    case 'semantic-release/minor': 
                        AMBIENTE = 'rc'
                        break
                    case 'semantic-release/major': 
                        AMBIENTE = 'rc'
                        break
                    case 'release': 
                        AMBIENTE = 'qa'
                        NAMESPACE = 'apiservice-microservicios'
                        break
                    case 'uat': 
                        AMBIENTE = 'uat'
                        NAMESPACE = 'apiservice-microservicios'
                        break
                    case 'preprod': 
                        AMBIENTE = 'preprod'
                        NAMESPACE = 'apiservice-microservicios'
                        break  
                    case 'master': 
                        AMBIENTE = 'master'
                        break
                    default:
                        println("Branch value error: " + branch)
                        currentBuild.getRawBuild().getExecutor().interrupt(Result.FAILURE)
                    }
                    
                    echo " --> Ambiente: ${AMBIENTE}"
                }
            }
        }
        stage('Stage: Build'){
            agent { 
                label "${jenkinsWorker}"
            }
            steps {
                script {
                    
                    def branch = "${env.BRANCH_NAME}"
                    
                    if (branch == "semantic-release/major"){
                    
                        echo "release version"
                    	//sh "mvn --batch-mode release:update-versions"
                    	APP_VERSION = readMavenPom().getVersion()
                    	def values = APP_VERSION.split('-')
                        def major = values[0].split('\\.')
                        def new_major = major[0].toInteger() + 1
                        APP_VERSION = "${new_major}.0.0-${AMBIENTE}"
                        echo "Version nueva: ${APP_VERSION}"
                        
                    }else if (branch == "semantic-release/minor"){
                    
                        echo "release version"
                    	//sh "mvn --batch-mode release:update-versions"
                    	APP_VERSION = readMavenPom().getVersion()
                    	def values = APP_VERSION.split('-')
                        def minor = values[0].split('\\.')
                        def new_minor = minor[1].toInteger() + 1
                        APP_VERSION = "${minor[0]}.${new_minor}.0-${AMBIENTE}"
                        echo "Version nueva: ${APP_VERSION}"
                        
                    }else if (branch == "semantic-release/patch"){
                    
                        echo "release version"
                    	sh "mvn --batch-mode release:update-versions"
                    	APP_VERSION = readMavenPom().getVersion()
                    	def values = APP_VERSION.split('-')
                        APP_VERSION = "${values[0]}-${AMBIENTE}"
                        echo "Version nueva: ${APP_VERSION}"
                        
                    }else if (branch != "master"){
                    
                        echo "environment version"
                        def version = readMavenPom().getVersion()
                    	APP_VERSION = "${version}-${branch}"
                        echo "Version : ${APP_VERSION}"
                        
                    }else{
                    
                    	echo "stable version"
                        APP_VERSION = readMavenPom().getVersion()
                        echo "Version : ${APP_VERSION}"
                        
                    }
                    
                    sh '\\cp infrastructure/src/main/resources/META-INF/microprofile-config-test.properties infrastructure/src/main/resources/META-INF/microprofile-config.properties'
                    sh 'mvn clean package -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true'
                    
                }
            }
        }
        stage('Stage: Test'){
            agent { 
                label "${jenkinsWorker}"
            }
            when { 
                not { 
                    branch 'master' 
                }
            }
            stages {
                stage("Unit Test") {
                    steps {
                        script {
                            sh 'mvn test'
                        }
                    }
                    
                }
                stage("Code Test") {
                    steps {
                        script {
                            withSonarQubeEnv('Sonar') {
                                echo " --> Sonar Scan"
                                sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar -Dsonar.projectKey=${APP_NAME}-${AMBIENTE} -Dsonar.projectName=${APP_NAME}-${AMBIENTE} -Dsonar.projectVersion=${APP_VERSION} -Dproject.settings=sonar/maven-sonar-project.properties"
                            }
                        }
                    }
                }
                stage('Kiuwan Test'){
                    steps {
                        script {
                            echo " --> Kiuwan Scan"
		                    kiuwan connectionProfileUuid: 'eh9q-SJTq',
		                    sourcePath: "${WORKSPACE}",
		                    applicationName: "${APP_NAME}",
		                    failureThreshold: 40.0,
		                    unstableThreshold: 90.0
		                    
		                    def kiuwanOutput = readJSON file: "${WORKSPACE}/kiuwan/output.json"
							def secRating = kiuwanOutput.Security.Rating
							
							echo "Rating : ${secRating}"
                        }
                    }
                }
            }
        }
        stage('Stage: Package'){
            stages {
		        stage('ECR Token') {
			        steps {
			            container('tools') {
			                script {
			                
			                    sh label: "",
			                    script: """
			                        #!/bin/bash
			                        
			                        echo " --> Login al Registry..."
			                        aws configure set aws_access_key_id ${AWS_ACCESS_KEY_ID}
									aws configure set aws_secret_access_key ${AWS_SECRET_ACCESS_KEY}
									aws configure set default.region ${AWS_REGION}
									aws configure set default.output json
			
			                    """
			                    env.LOGIN_DOCKER = sh(script:"aws ecr get-login --no-include-email | awk '{print \$6}'", returnStdout: true).trim()
			                    
			                }
			            }
			        }
		        }
		        stage('ECR Push') { 
		            agent { 
		                label "${jenkinsWorker}"
		            }
		            steps {
		                script {
		                    //echo "Maven build..."
		                    sh "\\cp infrastructure/src/main/resources/META-INF/microprofile-config-dev.properties infrastructure/src/main/resources/META-INF/microprofile-config.properties"
		                    sh "mvn clean package -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true"
		                    
		                    //sh "mvn verify -Pnative"
		                    //sh "mvn clean package -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true -Pnative -Dquarkus.native.container-build=true"
		                    
		                    echo "Docker Build..."
		                    sh "cd application && docker build -f src/main/docker/Dockerfile.jvm -t ${IMAGEN}:${APP_VERSION} ."
		                    //sh "cd application && docker build -f src/main/docker/Dockerfile.native -t ${IMAGEN}:${APP_VERSION} ."
		                    
		                    echo "Docker Tag..."
		                    sh "docker tag ${IMAGEN}:${APP_VERSION} ${PUSH}:${APP_VERSION}"
		
		                    echo "Docker Push..."
							sh "echo ${env.LOGIN_DOCKER} | docker login --username AWS --password-stdin https://${REGISTRY}"
		                    sh "docker push ${PUSH}:${APP_VERSION}"
		
		                }
		            }
		        }
			}
		}
        stage('Stage: Validate') {
            stages {
                stage("Container Scanner") {
                    steps {
                        container('tools') {
                            script {
                                echo "scanner Clair..."
                                sh label: "",
                                script: """
                                    #!/bin/bash

                                    set +xe
                                    
                                    # KLAR_TRACE=true
                                
                                	echo " --> Scanning image ${PUSH}:${APP_VERSION}..."
                                	SCAN=\$( CLAIR_ADDR=http://\$(oc get svc -l app=clair | awk '{print \$1}' | tail -1):6060 DOCKER_USER=AWS DOCKER_PASSWORD=${env.LOGIN_DOCKER} JSON_OUTPUT=true klar ${PUSH}:${APP_VERSION} )
                                    
                                    echo " --> Resultado del Scan: \$SCAN"

                                    echo " --> Validando el Scan..."
                                    RESULT=\$( echo \$SCAN | jq -r ".Vulnerabilities | .[] | .[] | .Severity" | grep -e Critical -e High )
                                    if [ "\$RESULT" == "" ]; then
                                        echo " --> Success! Imagen sin vulnerabilidades Critical ó High"
                                    elif [ "\$RESULT" =! "" ]; then
                                        echo " --> Error! Imagen con vulnerabilidades Critical ó High"
                                        echo " --> Scan: \$SCAN"
                                        exit 1
                                    else
                                        echo " --> Error! \$SCAN"
                                    fi

                                """      
                            }
                        }
                    }
                }
            }
        }
        stage('Stage: Deployment') {
            when {
		       not {
		          anyOf {
		            branch 'master'
		            branch 'semantic-release/patch'
		            branch 'semantic-release/minor'
		            branch 'semantic-release/major'
		          }
		       }
		    }
            steps {
                container('tools') {
                    script {
                        openshift.withCluster() {
                            openshift.withProject("${NAMESPACE}") {
                            
                                sh label: "",
	                            script: """
	                                #!/bin/bash
	                                
	                                cat << EOF > openshift/.dockercfg
{"$REGISTRY":{"username":"AWS","password":"$env.LOGIN_DOCKER"}}
EOF
	
	                            """
								
	                            if (openshift.selector("secrets", "ecr-registry").exists()){
                            		sh "oc set data secret/ecr-registry --from-file=.dockercfg=openshift/.dockercfg"
                            	}else{
	                            	sh "oc create secret generic ecr-registry --from-file=.dockercfg=openshift/.dockercfg --type=kubernetes.io/dockercfg"
	                            	sh "oc secrets link default ecr-registry --for=pull"
	                            }
	                            
                                // Validando
                                if (!openshift.selector("dc", "${APP_NAME}-${AMBIENTE}").exists()){
                                    
                                    // DeploymemtConfig
                                    echo " --> Deploy..."
                                    def app = openshift.newApp("--file=./openshift/template.yaml", "--param=APP_NAME=${APP_NAME}-${AMBIENTE}", "--param=APP_VERSION=${APP_VERSION}", "--param=AMBIENTE=${AMBIENTE}", "--param=REGISTRY=${PUSH}:${APP_VERSION}" )
                                    
                                    def dc = openshift.selector("dc", "${APP_NAME}-${AMBIENTE}")
                                    while (dc.object().spec.replicas != dc.object().status.availableReplicas) {
                                        sleep 10
                                    }
                                    echo " --> Desployed $APP_NAME!"
                                }
                                else {
                                    echo " --> Ya existe el Deployment $APP_NAME-${AMBIENTE}!"
                                    echo " --> Updating Deployment..."
                                    sh "oc process -f ./openshift/template.yaml -p APP_NAME=${APP_NAME}-${AMBIENTE} -p APP_VERSION=${APP_VERSION} -p AMBIENTE=${AMBIENTE} -p REGISTRY=${PUSH}:${APP_VERSION} | oc apply -f -"
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Stage: Deployment Test') {
            when {
		       not {
		          anyOf {
		            branch 'master'
		            branch 'semantic-release/patch'
		            branch 'semantic-release/minor'
		            branch 'semantic-release/major'
		          }
		       }
		    }
            steps {
                container('tools') {
                    script {
                        openshift.withCluster() {
                            openshift.withProject("${NAMESPACE}"){
                                // Validando el Deployment
                                echo " --> Validando el status del Deployment"
                                if (openshift.selector("dc", "${APP_NAME}-${AMBIENTE}").exists()){
                                    def latestDeploymentVersion = openshift.selector('dc',"${APP_NAME}-${AMBIENTE}").object().status.latestVersion
                                    sh "echo ${latestDeploymentVersion}"
                                    def rc = openshift.selector('rc', "${APP_NAME}-${AMBIENTE}-${latestDeploymentVersion}")
                                    rc.untilEach(1){
                                        def rcMap = it.object()
                                        return (rcMap.status.replicas.equals(rcMap.status.readyReplicas))
                                    }
                                    
                                    def dc = openshift.selector('dc', "${APP_NAME}-${AMBIENTE}")
                                    def status = dc.rollout().status()
                
                                    // Validando el Service 
                                    sh "echo Validando el Service"
                                    def connected = openshift.verifyService("${APP_NAME}-${AMBIENTE}")
                                    if (connected) {
                                        echo "Able to connect to ${APP_NAME}-${AMBIENTE}"
                                    } else {
                                        echo "Unable to connect to ${APP_NAME}-${AMBIENTE}"
                                        rollback()
                                    }
                                } 
                                else {
                                    echo " --> No existe el Deployment $APP_NAME-${AMBIENTE}!"
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Stage: Quality Test'){
        	when {
                branch 'release'
            }
            agent { 
                label "${jenkinsWorker}"
            }
            stages {
		        stage('Functional Test') {
		            steps {
		                script {
		                    try {
								sh 'cd test && npm install'
		                        sh 'cd test && npm test'
					    
								sh 'cd test && cp reports.json $WORKSPACE'
		                        cucumber buildStatus: 'SUCCESS', fileIncludePattern: 'reports.json'
		                    } catch (e) {
								sh 'cd test && cp reports.json $WORKSPACE'
		                        cucumber buildStatus: 'FAIL', fileIncludePattern: 'reports.json'
		                    }
		                }
		            }
		        }
		        stage('Report Functional Test') {
		            steps {
		                script {
		                    echo " --> Reporte Cucumber..."
		                    echo "REPORT-TEST: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}cucumber-html-reports/overview-features.html"
		                }
		            }
		        }
	    	}
    	}
        stage('Stage: Release') {
            when { 
                not { 
                    branch 'develop' 
                }
            }
            agent { 
                label "${jenkinsWorker}"
            }
            steps {
                script {
                    echo " --> Release..."
                    def branch = "${env.BRANCH_NAME}"
                    def release = "v${APP_VERSION}"

					if (branch == "semantic-release/patch" || branch == "semantic-release/minor" || branch == "semantic-release/major"){
					
                    	def values = APP_VERSION.split('-')
                    	sh "mvn --batch-mode release:update-versions -DdevelopmentVersion=${values[0]}-SNAPSHOT"
                    	
	                    // Credentials
	                    withCredentials([usernamePassword(credentialsId: 'mponce-apiservice', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
	                        sh label: "", 
	                        script: """
	                            #!/bin/bash
	                            
	                            git config --local credential.helper "!f() { echo username=\\${GIT_USERNAME}; echo password=\\${GIT_PASSWORD}; }; f"
	                            
	                            echo " --> commit release candidate..."
	                        	git add -A
								git commit -m "add release ${release}"
								git push --force origin HEAD:${env.BRANCH_NAME}
	                            
	                            echo " --> create tag..."
	                            git tag ${release}
	                            git push --force origin ${release}
	                        
	                        """
	                    }
                    }else{
                    	// Credentials
	                    withCredentials([usernamePassword(credentialsId: 'mponce-apiservice', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
	                        sh label: "", 
	                        script: """
	                            #!/bin/bash
	                            
	                            git config --local credential.helper "!f() { echo username=\\${GIT_USERNAME}; echo password=\\${GIT_PASSWORD}; }; f"
	                            
	                            echo " --> create tag..."
	                            git tag ${release}
	                            git push --force origin ${release}
	                        
	                        """
	                    }
                    }
                }
            }
        }
        stage('Stage: Rollback') {
            when { 
                not {
                    anyOf { 
                        branch 'develop'
                        branch 'master'
                        branch 'semantic-release/patch'
		            	branch 'semantic-release/minor'
		            	branch 'semantic-release/major'
                    }
                }
            }
            steps {
                container('tools') {
                    timeout(time: 5, unit: 'MINUTES') {
                        script {
                            openshift.withCluster() {
                                openshift.withProject("${NAMESPACE}"){
                                    def userInputDeploy = ""

                                    userInputDeploy = input(
                                        message: '¿Ejecutar Rollback?', 
                                        ok: 'Confirmar', 
                                        parameters: [[$class: 'ChoiceParameterDefinition', 
                                        choices: 'SI\nNO\nCancelar',
                                        name: 'Seleccionar',
                                        description: 'Seleccione una opción']]
                                    )

                                    if (userInputDeploy == "SI") {
                                        // do action
                                        echo " --> Ejecutamos el rollback..."
                                        rollback()
                                    } 
                                    else if (userInputDeploy == "NO") {
                                        echo " --> No ejecutamos el rollback..."
                                    }
                                    else {
                                        // not do action
                                        echo "Action was aborted."
                                    }
                                }
                            }
                        }   
                    }
                }
            }
        }
    }
    post {
        success {
            echo " ==> SUCCES: Pipeline successful."
        }
        failure {
            echo " ==> ERROR: Pipeline failed."
        }
        always {
            node("${jenkinsWorker}") {
                // Clean Up
                script {
                    echo " ==> Cleanup..."
                    sh "docker rmi -f \$( docker images | grep none | awk '{print \$3}' ) || true"
                }
                step([$class: 'WsCleanup'])
            }
        }
    }
}

def rollback(){
    echo " --> Rollback..."
    
    REVISION = sh (script: "oc rollout history dc ${APP_NAME}-${AMBIENTE} | grep Complete | awk '{print \$1}' | tail -1 | awk '{print \$0-1}'", returnStdout:true).trim()

    echo " --> Revision: ${REVISION}"
    rollback = openshift.selector("dc/${APP_NAME}-${AMBIENTE}").rollout().undo("--to-revision=${REVISION}")
    // def result = rollback.history()
    
    def dc = openshift.selector('dc', "${APP_NAME}-${AMBIENTE}")
    // this will wait until the desired replicas are available
    dc.rollout().status()
}

