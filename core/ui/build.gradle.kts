plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.compose")
    id("nito.primitive.kmp.android.hilt")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.ui"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.designsystem)
                implementation(projects.core.model)
                implementation(projects.core.data)
            }
        }
    }
}
