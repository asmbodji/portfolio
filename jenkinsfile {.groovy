pipeline {
    agent any

    environment {
        // Définir des variables d'environnement ici si nécessaire
        DEPLOY_ENV = 'staging'
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloner le dépôt Git
                git 'https://github.com/asmbodji/portfolio.git'
            }
        }

        stage('Test') {
            steps {
                // Exécuter les tests unitaires Python (par exemple, avec pytest)
                script {
                    sh 'pytest tests/'
                }
            }
        }

        stage('Build') {
            steps {
                // Construire l'image Docker (si nécessaire)
                script {
                    sh 'docker build -t ton-image .'
                }
            }
        }

        stage('Deploy') {
            steps {
                // Déployer l'application (exemple : déploiement sur un serveur de test)
                script {
                    sh 'ssh user@serveur_de_test "docker run -d ton-image"'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
