@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.teamfairy.fe.application")
    id("com.teamfairy.fe.test")
}

android {
    namespace = "com.teamfairy"

    configurations {
        implementation {
            exclude(group = "org.jetbrains", module = "annotations")
        }
    }

    defaultConfig {
        applicationId = "com.teamfairy.fe"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":feature"))
    implementation(project(":core-ui"))
    implementation(project(":domain"))
    implementation(project(":data"))

    // AndroidX
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.fragment.ktx)
    implementation(libs.splash.screen)

    // Third Party
    implementation(libs.coil.core)
    implementation(libs.bundles.retrofit)
}