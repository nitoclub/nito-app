plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.js")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.common"

kotlin {
    explicitApi()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinxCoroutinesCore)
                implementation(libs.kotlinxDatetime)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.koin)
            }
        }
    }
}
