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

// If we use jvmToolchain, we need to install JDK 11
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "11"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.bundles.plugins)
    // https://github.com/google/dagger/issues/3068#issuecomment-1470534930
    implementation(libs.javaPoet)
}

gradlePlugin {
    plugins {
        // Primitives
        register("androidApplication") {
            id = "nito.primitive.androidapplication"
            implementationClass = "club.nito.primitive.AndroidApplicationPlugin"
        }
        register("android") {
            id = "nito.primitive.android"
            implementationClass = "club.nito.primitive.AndroidPlugin"
        }
        register("androidKotlin") {
            id = "nito.primitive.android.kotlin"
            implementationClass = "club.nito.primitive.AndroidKotlinPlugin"
        }
        register("androidCompose") {
            id = "nito.primitive.android.compose"
            implementationClass = "club.nito.primitive.AndroidComposePlugin"
        }
        register("androidHilt") {
            id = "nito.primitive.android.hilt"
            implementationClass = "club.nito.primitive.AndroidHiltPlugin"
        }
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
        register("kmpAndroid") {
            id = "nito.primitive.kmp.android"
            implementationClass = "club.nito.primitive.KmpAndroidPlugin"
        }
        register("kmpCompose") {
            id = "nito.primitive.kmp.compose"
            implementationClass = "club.nito.primitive.KmpComposePlugin"
        }
        register("kmpKtorfit") {
            id = "nito.primitive.kmp.ktorfit"
            implementationClass = "club.nito.primitive.KmpKtorfitPlugin"
        }
        register("kmpAndroidHilt") {
            id = "nito.primitive.kmp.android.hilt"
            implementationClass = "club.nito.primitive.KmpAndroidHiltPlugin"
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

        // Conventions
        register("androidFeature") {
            id = "nito.convention.androidfeature"
            implementationClass =
                "club.nito.convention.AndroidFeaturePlugin"
        }
    }
}
