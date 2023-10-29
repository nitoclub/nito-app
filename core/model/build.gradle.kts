plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.android.hilt")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.model"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinxCoroutinesCore)
            }
        }
    }
}
