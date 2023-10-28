plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.android.hilt")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.data"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
            }
        }
    }
}
dependencies {
}
