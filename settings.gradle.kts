enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "nito"

include(
    ":app:shared",
    ":app:android",
    ":app:ios-combined",
    ":app:web",
    ":app:backend",
    ":core:common",
    ":core:database",
    ":core:datastore",
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
