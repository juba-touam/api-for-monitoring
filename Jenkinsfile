node("ci-node") {
	def GIT_COMMIT_HASH = ""

	stage("Checkout") {
		checkout scm
		GIT_COMMIT_HASH = sh(script: "git log -n 1 --pretty=format:%h", returnStdout: true).trim()
	}

	//stage("Unit tests") {
	//	sh "chmod +x mvnw && ./mvnw clean test"
	//}

	//stage("Quality Analysis") {
		//withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
		//	sh """
          //      ./mvnw clean verify sonar:sonar \\
           //       -Dsonar.projectKey=api-for-monitoring \\
             //     -Dsonar.projectName='api-for-monitoring' \\
               //   -Dsonar.host.url=https://sonar.check-consulting.net \\
                 // -Dsonar.token=${SONAR_TOKEN}
          // """
	//	}
	//}

	stage("Build Jar file") {
		sh "./mvnw package -DskipTests"
	}

	stage("Build Docker Image") {
		sh "docker build -t mchekini/api-for-monitoring:${GIT_COMMIT_HASH} ."
	}

	stage("Push Docker Image") {
		withCredentials([usernamePassword(credentialsId: 'mchekini', usernameVariable: 'username', passwordVariable: 'password')]) {
			sh "echo \$password | docker login -u \$username --password-stdin"
			sh "docker push mchekini/api-for-monitoring:${GIT_COMMIT_HASH}"
			sh "docker rmi mchekini/api-for-monitoring:${GIT_COMMIT_HASH}"
		}
	}
}
