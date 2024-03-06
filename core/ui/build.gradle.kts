plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.compose")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.ui"

kotlin {
    explicitApi()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.designsystem)
                implementation(projects.core.model)
                implementation(projects.core.domain)
                implementation(projects.core.data)

                implementation(libs.precompose)
                api(libs.precomposeViewmodel)
                implementation(libs.precomposeKoin)

                implementation(libs.koin)
                implementation(libs.koinCompose)

                implementation(libs.kermit)
            }
        }
    }
}
