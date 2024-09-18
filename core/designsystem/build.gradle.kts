plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.compose")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.designsystem"

kotlin {
    explicitApi()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.materialIconsExtended)
                api(libs.coil)
                api(libs.coilNetwork)
            }
        }
    }
}
