plugins {
    `kotlin-dsl`
}

group = "com.teamfairy.fe.buildlogic"

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradleplugin)
}

gradlePlugin {
    plugins {
        create("android-application") {
            id = "com.teamfairy.fe.application"
            implementationClass = "com.teamfairy.fe.plugin.AndroidApplicationPlugin"
        }
        create("android-feature") {
            id = "com.teamfairy.fe.feature"
            implementationClass = "com.teamfairy.fe.plugin.AndroidFeaturePlugin"
        }
        create("android-kotlin") {
            id = "com.teamfairy.fe.kotlin"
            implementationClass = "com.teamfairy.fe.plugin.AndroidKotlinPlugin"
        }
        create("android-hilt") {
            id = "com.teamfairy.fe.hilt"
            implementationClass = "com.teamfairy.fe.plugin.AndroidHiltPlugin"
        }
        create("kotlin-serialization") {
            id = "com.teamfairy.fe.serialization"
            implementationClass = "com.teamfairy.fe.plugin.KotlinSerializationPlugin"
        }
        create("android-test") {
            id = "com.teamfairy.fe.test"
            implementationClass = "com.teamfairy.fe.plugin.AndroidTestPlugin"
        }
    }
}