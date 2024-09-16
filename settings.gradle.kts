rootProject.name = "nito"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
        }
        maven { url = uri("https://jitpack.io") }
    }
}

include(
    ":app:shared",
    ":app:android",
    ":app:ios-combined",
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
