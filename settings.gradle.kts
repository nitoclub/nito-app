enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "nito"

include(
    ":app:android",
    ":app:ios-combined",
    ":core:common",
    ":core:database",
    ":core:network",
    ":core:data",
    ":core:model",
    ":core:ui",
    ":core:designsystem",
    ":core:domain",
    ":feature:top",
    ":feature:auth",
    ":feature:schedule",
    ":feature:settings",
)
