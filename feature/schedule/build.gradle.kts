plugins {
    id("nito.convention.feature")
}

android.namespace = "club.nito.feature.schedule"

kotlin {
    explicitApi()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.model)
                implementation(projects.core.domain)
                implementation(projects.core.ui)
                implementation(projects.core.designsystem)

                implementation(libs.kotlinxCoroutinesCore)

                implementation(project.dependencies.platform(libs.koinBom))
                implementation(libs.koin)
            }
        }
    }
}
