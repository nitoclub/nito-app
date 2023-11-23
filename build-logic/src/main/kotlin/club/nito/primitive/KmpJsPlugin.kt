package club.nito.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KmpJsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            kotlin {
                js {
                    browser()
                }
            }
        }
    }
}
