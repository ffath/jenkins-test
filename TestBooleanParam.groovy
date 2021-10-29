pipeline {
    agent any

    parameters {
        booleanParam(name: 'SMARTLIB_WRAPPER', defaultValue: false, description: 'Build the Bpk Smart Lib wrapper')
    }

    stages {
        stage('Hello') {
            steps {
                script {
                    echo """SMARTLIB_WRAPPER = ${params.SMARTLIB_WRAPPER}"""
                    if (params.SMARTLIB_WRAPPER) {
                        echo 'SMARTLIB_WRAPPER = true'
                    }
                    else {
                        echo 'SMARTLIB_WRAPPER = false'
                    }
                }
            }
        }
    }
}
