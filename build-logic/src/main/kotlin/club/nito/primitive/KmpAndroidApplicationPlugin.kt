package club.nito.primitive

import com.google.devtools.ksp.gradle.KspTaskMetadata
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class KmpAndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }
            kotlin {
                androidTarget {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = "17"
                        }
                    }
                }
            }
            android {
                setupAndroid()
            }
            // https://slack-chats.kotlinlang.org/t/13166064/been-discovering-that-the-task-kspcommonmainkotlinmetadata-i#9a50fa1b-1ec5-47c2-9172-2a5780a1900e
            tasks.withType<KspTaskMetadata>().configureEach {
                notCompatibleWithConfigurationCache("Configuration cache not supported due to serialization")
            }
        }
    }
}
