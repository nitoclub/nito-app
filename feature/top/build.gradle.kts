plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.android.hilt")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.compose")
    id("nito.primitive.kotest")
}

android.namespace = "club.nito.feature.top"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.model)
                implementation(projects.core.domain)
                implementation(projects.core.ui)
                implementation(projects.core.designsystem)

                implementation(libs.kotlinxCoroutinesCore)

                implementation(libs.koin)
                implementation(libs.koinCompose)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.composeHiltNavigtation)
            }
        }
    }
}
