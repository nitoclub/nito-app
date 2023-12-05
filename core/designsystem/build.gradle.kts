plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.js")
    id("nito.primitive.kmp.compose")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.designsystem"

kotlin {
    explicitApi()

    js {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.imageLoader)
            }
        }
    }
}
