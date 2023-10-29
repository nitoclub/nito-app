plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.android.hilt")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.compose")
}

android.namespace = "club.nito.feature.settings"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.model)
                implementation(projects.core.ui)
                implementation(projects.core.designsystem)

                implementation(libs.kotlinxCoroutinesCore)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.composeHiltNavigtation)
            }
        }
    }
}
