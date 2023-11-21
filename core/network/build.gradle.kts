plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.android.hilt")
    id("nito.primitive.detekt")
    id("nito.primitive.kmp.serialization")
}

android.namespace = "club.nito.core.network"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.model)

                implementation(libs.kotlinxCoroutinesCore)

                implementation(libs.koin)
                implementation(libs.kermit)

                implementation(libs.okIo)
                implementation(libs.ktorClientCore)
                implementation(libs.ktorKotlinxSerialization)
                implementation(libs.ktorClientContentNegotiation)

                implementation(libs.supabaseGotrue)
                implementation(libs.supabasePostgrest)
                implementation(libs.supabaseRealtime)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktorClientOkHttp)
                implementation(libs.multiplatformFirebaseAuth)
                implementation(libs.okHttpLoggingInterceptor)
                implementation(libs.okHttpLoggingInterceptor)
                implementation(libs.firebaseRemoteConfig)
                implementation(libs.androidxLifecycleProcess)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktorClientDarwin)
            }
        }
    }
}
