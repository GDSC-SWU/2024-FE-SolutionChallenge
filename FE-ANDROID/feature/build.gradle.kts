@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.teamfairy.fe.feature")
    id("com.teamfairy.fe.test")
}

android {
    namespace = "com.teamfairy.feature"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":domain"))

    // AndroidX
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.fragment.ktx)
    implementation(libs.splash.screen)
    implementation(libs.recycler)

    // Third Party
    implementation(libs.coil.core)
    implementation(libs.bundles.retrofit)
}