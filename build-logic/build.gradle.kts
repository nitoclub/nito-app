import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "club.nito.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

// If we use jvmToolchain, we need to install JDK 17
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.bundles.plugins)
    // https://github.com/google/dagger/issues/3068#issuecomment-1470534930
    implementation(libs.javaPoet)
}

gradlePlugin {
    plugins {
        // Primitives
        register("androidCrashlytics") {
            id = "nito.primitive.android.crashlytics"
            implementationClass = "club.nito.primitive.AndroidCrashlyticsPlugin"
        }
        register("androidFirebase") {
            id = "nito.primitive.android.firebase"
            implementationClass = "club.nito.primitive.AndroidFirebasePlugin"
        }
        register("kmp") {
            id = "nito.primitive.kmp"
            implementationClass = "club.nito.primitive.KmpPlugin"
        }
        register("kmpIos") {
            id = "nito.primitive.kmp.ios"
            implementationClass = "club.nito.primitive.KmpIosPlugin"
        }
        register("kmpAndroidApplication") {
            id = "nito.primitive.kmp.androidapplication"
            implementationClass = "club.nito.primitive.KmpAndroidApplicationPlugin"
        }
        register("kmpAndroid") {
            id = "nito.primitive.kmp.android"
            implementationClass = "club.nito.primitive.KmpAndroidPlugin"
        }
        register("kmpJs") {
            id = "nito.primitive.kmp.js"
            implementationClass = "club.nito.primitive.KmpJsPlugin"
        }
        register("kmpCompose") {
            id = "nito.primitive.kmp.compose"
            implementationClass = "club.nito.primitive.KmpComposePlugin"
        }
        register("kmpKotlinSerialization") {
            id = "nito.primitive.kmp.serialization"
            implementationClass = "club.nito.primitive.KotlinSerializationPlugin"
        }
        register("detekt") {
            id = "nito.primitive.detekt"
            implementationClass = "club.nito.primitive.DetektPlugin"
        }
        register("ossLicenses") {
            id = "nito.primitive.android.osslicenses"
            implementationClass = "club.nito.primitive.OssLicensesPlugin"
        }
        register("kotest") {
            id = "nito.primitive.kotest"
            implementationClass = "club.nito.primitive.KotestPlugin"
        }

        // Conventions
        register("feature") {
            id = "nito.convention.feature"
            implementationClass = "club.nito.convention.FeaturePlugin"
        }
    }
}
