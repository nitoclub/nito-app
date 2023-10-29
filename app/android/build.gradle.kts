plugins {
    id("nito.primitive.androidapplication")
    id("nito.primitive.android.kotlin")
    id("nito.primitive.android.compose")
}

android {
    namespace = "club.nito.android"
    compileSdk = 34
    defaultConfig {
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
//    implementation(libs.compose.ui)
//    implementation(libs.compose.ui.tooling.preview)
//    implementation(libs.compose.material3)
//    implementation(libs.androidx.activity.compose)
//    debugImplementation(libs.compose.ui.tooling)

    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)

    implementation(projects.feature.top)
    implementation(projects.feature.auth)
    implementation(projects.feature.schedule)
    implementation(projects.feature.settings)

    implementation(libs.composeNavigation)
    implementation(libs.composeHiltNavigtation)
    implementation(libs.composeMaterialWindowSize)
    implementation(libs.androidxBrowser)
    implementation(libs.androidxWindow)
    implementation(libs.kermit)
    implementation(libs.androidxSplashScreen)
//    implementation(libs.firebaseDynamicLinks)
//    testImplementation(projects.core.testing)
}
