plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.android.hilt")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.domain"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.data)
                implementation(projects.core.model)

                implementation(libs.kotlinxCoroutinesCore)
                implementation(libs.kermit)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.koin)
            }
        }
    }
}
