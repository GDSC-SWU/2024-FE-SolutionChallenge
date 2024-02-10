@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.teamfairy.fe.feature")
    id("com.teamfairy.fe.test")
}

android {
    namespace = "com.teamfairy.data"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))

    //android
    implementation(libs.bundles.room)
    implementation(libs.paging)

    // Third Party
    implementation(libs.bundles.retrofit)
}