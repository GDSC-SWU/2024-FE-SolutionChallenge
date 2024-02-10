@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.teamfairy.fe.feature")
}

android {
    namespace = "com.teamfairy.core_ui"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
}