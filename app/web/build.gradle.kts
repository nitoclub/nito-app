plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.js")
    id("nito.primitive.kmp.compose")
}

kotlin {
    applyDefaultHierarchyTemplate()

    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(projects.app.shared)

                implementation(projects.core.common)
                implementation(projects.core.model)
                implementation(projects.core.data)
                implementation(projects.core.network)
                implementation(projects.core.domain)
                implementation(projects.core.designsystem)
                implementation(projects.core.ui)

                implementation(projects.feature.top)
                implementation(projects.feature.auth)
                implementation(projects.feature.schedule)
                implementation(projects.feature.settings)

                implementation(compose.html.core)
                implementation(compose.materialIconsExtended)

                implementation(libs.precompose)
                implementation(libs.kermit)

                implementation(libs.koinCompose)
            }
        }
    }
}

compose.experimental {
    web.application {}
}
