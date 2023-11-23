import java.io.FileInputStream
import java.util.Properties

plugins {
    id("nito.primitive.androidapplication")
    id("nito.primitive.android.kotlin")
    id("nito.primitive.android.compose")
    id("nito.primitive.android.hilt")
    id("nito.primitive.android.firebase")
    id("nito.primitive.android.crashlytics")
}

val keystorePropertiesFile = project.file("keystore.properties")
val keystoreExits = keystorePropertiesFile.exists()

android {
    namespace = "club.nito.app"
    compileSdk = 34
    defaultConfig {
        minSdk = 31
        targetSdk = 34
        versionCode = 3
        versionName = "0.2.1"
    }
    signingConfigs {
        create("dev") {
            storeFile = project.file("dev.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }

        if (keystoreExits) {
            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))
            create("prod") {
                keyAlias = keystoreProperties["keyAlias"] as String?
                keyPassword = keystoreProperties["keyPassword"] as String?
                storeFile = project.file("prod.keystore")
                storePassword = keystoreProperties["storePassword"] as String?
            }
        }
    }

    flavorDimensions += "network"
    productFlavors {
        create("dev") {
            signingConfig = signingConfigs.getByName("dev")
            isDefault = true
            applicationIdSuffix = ".dev"
            dimension = "network"
        }
        create("prod") {
            dimension = "network"
            signingConfig = if (keystoreExits) {
                signingConfigs.getByName("prod")
            } else {
                signingConfigs.getByName("dev")
            }
        }
    }
    buildTypes {
        debug {
            signingConfig = null
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.app.shared)

    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.network)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)

    implementation(projects.feature.top)
    implementation(projects.feature.auth)
    implementation(projects.feature.schedule)
    implementation(projects.feature.settings)

    implementation(libs.precompose)
    implementation(libs.composeNavigation)
    implementation(libs.composeHiltNavigtation)
    implementation(libs.composeMaterialWindowSize)
    implementation(libs.androidxBrowser)
    implementation(libs.androidxWindow)
    implementation(libs.kermit)
    implementation(libs.androidxSplashScreen)

    implementation(libs.koinCompose)
//    implementation(libs.firebaseDynamicLinks)
//    testImplementation(projects.core.testing)
}
