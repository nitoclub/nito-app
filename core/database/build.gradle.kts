plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.js")
    id("nito.primitive.kmp.sqldelight")
    id("nito.primitive.detekt")
}

android.namespace = "club.nito.core.database"

kotlin {
    explicitApi()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.model)

                implementation(libs.kotlinxCoroutinesCore)

                implementation(libs.koin)
                implementation(libs.kermit)
            }
        }
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("club.nito.core.database")
        }
    }
}
