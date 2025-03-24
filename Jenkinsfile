node("ci-node") {
	def GIT_COMMIT_HASH = ""

	stage("Checkout"){
		checkout scm
		GIT_COMMIT_HASH = sh (script: "git log -n 1 --pretty=format:'%H'", returnStdout: true)
	}

	/*
	stage("Unit tests") {
		sh "chmod +x mvnw && ./mvnw clean test"
	}

	stage("Quality Analysis") {
		withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
			sh """
				chmod +x mvnw
				./mvnw clean verify sonar:sonar \\
					-Dsonar.projectKey=api-for-monitoring \\
					-Dsonar.projectName='api-for-monitoring' \\
					-Dsonar.host.url=https://sonar.check-consulting.net \\
					-Dsonar.token=${SONAR_TOKEN}
			"""
		}
	}
	*/

	stage("Build Jar file"){

		sh 'chmod +x mvnw'
		sh './mvnw package -DskipTests'
	}

	stage("Build Docker Image") {
		sh "sudo docker build -t mchekini/api-for-monitoring:${GIT_COMMIT_HASH} ."
	}

	stage("Push Docker Image") {
		withCredentials([usernamePassword(credentialsId: 'mchekini', passwordVariable: 'password', usernameVariable: 'username')]) {
			sh "sudo docker login -u $username -p $password"
			sh "sudo docker push mchekini/api-for-monitoring:${GIT_COMMIT_HASH}"
			sh "sudo docker rmi mchekini/api-for-monitoring:${GIT_COMMIT_HASH}"
		}
	}
}
