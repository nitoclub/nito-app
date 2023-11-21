package club.nito.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KotestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.kotest.multiplatform")
            }
            android {
                testOptions {
                    unitTests.all {
                        it.useJUnitPlatform()
                    }
                }
            }
            kotlin {
                with(sourceSets) {
                    getByName("commonTest").apply {
                        dependencies {
                            implementation(platform(libs.library("kotestBom")))
                            implementation(libs.library("kotestAssertionsCore"))
                            implementation(libs.library("kotestFrameworkEngine"))
                            implementation(libs.library("kotestFrameworkDatatest"))
                        }
                    }
                }
            }
            dependencies {
                testImplementation(libs.library("kotestRunnerJunit5"))
            }
        }
    }
}
