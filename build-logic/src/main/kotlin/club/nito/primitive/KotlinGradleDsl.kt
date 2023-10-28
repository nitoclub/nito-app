package club.nito.primitive

import com.android.build.gradle.TestedExtension
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun DependencyHandlerScope.kapt(
    artifact: MinimalExternalModuleDependency,
) {
    add("kapt", artifact)
}

internal fun TestedExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal fun DependencyHandlerScope.ksp(
    artifact: MinimalExternalModuleDependency,
) {
    add("ksp", artifact)
}

internal fun DependencyHandlerScope.kaptTest(
    artifact: MinimalExternalModuleDependency,
) {
    add("kaptTest", artifact)
}
