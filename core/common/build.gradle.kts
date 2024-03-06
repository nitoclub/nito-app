plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
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
                implementation(libs.kotlinSerializationJson)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.koin)
            }
        }
    }
}
