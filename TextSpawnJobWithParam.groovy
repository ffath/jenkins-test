pipeline {
    agent any

    parameters {
        choice(name: 'DRM', choices: ['Fairplay', 'PlayReady', 'PlayreadyLegacy', 'PlayReady_Widevine', 'Verimatrix', 'VoDrm', 'Widevine'], description: 'DrmScheme to support')
    }

    stages {
        stage('Hello') {
            steps {
                script {
                    echo """DRM = ${params.DRM}"""
                    
                    // whether to build the SmartLib wrapper or not
                    def buildWrapper = false
                    if ("${params.DRM}" == "Widevine") {
                        buildWrapper = true
                    }
                    
                    echo """buildWrapper = ${buildWrapper}"""

                    build job: "./TestBooleanParam",
                        parameters: [
                            string(name:'SMARTLIB_WRAPPER', value: '${buildWrapper}')],
                        propagate: true
                }
            }
        }
    }
}
