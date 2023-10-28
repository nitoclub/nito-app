package club.nito.primitive

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = "11"
            }

            android {
                kotlinOptions {
                    // Treat all Kotlin warnings as errors (disabled by default)
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-Xcontext-receivers"
                    )

                    jvmTarget = JavaVersion.VERSION_11.toString()
                }
            }
            dependencies {
                implementation(libs.library("kotlinxCoroutinesCore"))
                // Fix https://youtrack.jetbrains.com/issue/KT-41821
                implementation(libs.library("kotlinxAtomicfu"))
            }
        }
    }
}
