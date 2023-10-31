plugins {
    alias(libs.plugins.androidGradlePlugin) apply false
    alias(libs.plugins.androidGradleLibraryPlugin) apply false
    alias(libs.plugins.kotlinGradlePlugin) apply false
    alias(libs.plugins.kotlinxKover) apply false
}

//tasks.register("clean", Delete::class) {
//    delete(rootProject.layout.buildDirectory)
//}

buildscript {
    configurations.all {
        resolutionStrategy.eachDependency {
            when {
                requested.name == "javapoet" -> useVersion("1.13.0")
            }
        }
    }
}

//plugins {
//    //trick: for the same plugin versions in all sub-modules
//    alias(libs.plugins.androidApplication).apply(false)
//    alias(libs.plugins.androidLibrary).apply(false)
//    alias(libs.plugins.kotlinAndroid).apply(false)
//    alias(libs.plugins.kotlinMultiplatform).apply(false)
//}

