package club.nito.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KmpSqlDelightPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("app.cash.sqldelight")
            }
            kotlin {
                with(sourceSets) {
                    getByName("commonMain").apply {
                        dependencies {
                            implementation(libs.library("sqldelightPrimitiveAdapters"))
                            implementation(libs.library("sqldelightCoroutinesExtensions"))
                        }
                    }
                    findByName("androidMain")?.apply {
                        dependencies {
                            implementation(libs.library("sqldelightAndroidDriver"))
                        }
                    }
                    findByName("iosMain")?.apply {
                        dependencies {
                            implementation(libs.library("sqldelightNativeDriver"))

                            // Need to explicitly depend on these, otherwise the build fails.
                            implementation(libs.library("statelyCommon"))
                            implementation(libs.library("statelyIsolate"))
                            implementation(libs.library("statelyIsoCollections"))
                        }
                    }
                    findByName("jsMain")?.apply {
                        dependencies {
                            implementation(libs.library("sqldelightWebWorkerDriver"))
                            implementation(npm("sql.js", "1.6.2"))
                            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
                        }
                    }
                }
            }
        }
    }
}
