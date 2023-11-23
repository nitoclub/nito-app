plugins {
    id("nito.primitive.kmp")
    alias(libs.plugins.composeGradlePlugin)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.designsystem)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(kotlin("test"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
            }
        }
    }
}

compose.experimental {
    web.application {}
}

//tasks.getByPath("jsProcessResources").dependsOn("libresGenerateResources")

