plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.domain"

kotlin {
    explicitApi()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.data)
                implementation(projects.core.model)

                implementation(libs.kotlinxCoroutinesCore)

                implementation(project.dependencies.platform(libs.koinBom))
                implementation(libs.koin)
                implementation(libs.kermit)
            }
        }
    }
}
