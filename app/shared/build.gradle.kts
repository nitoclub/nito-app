plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.js")
    id("nito.primitive.kmp.compose")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.app.shared"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.model)
                implementation(projects.core.database)
                implementation(projects.core.datastore)
                implementation(projects.core.data)
                implementation(projects.core.network)
                implementation(projects.core.domain)
                implementation(projects.core.designsystem)
                implementation(projects.core.ui)

                implementation(projects.feature.top)
                implementation(projects.feature.auth)
                implementation(projects.feature.schedule)
                implementation(projects.feature.settings)

                implementation(libs.kotlinxCoroutinesCore)

                implementation(libs.koin)
                implementation(libs.koinCompose)

                implementation(libs.kermit)
                implementation(libs.kermitKoin)
            }
        }
    }
}
