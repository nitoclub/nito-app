import org.jetbrains.compose.internal.utils.getLocalProperty

plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.android")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.detekt")
    id("nito.primitive.kmp.serialization")
    alias(libs.plugins.apollographql)
}

android.namespace = "club.nito.core.network"

kotlin {
    explicitApi()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.core.model)

                implementation(libs.kotlinxCoroutinesCore)

                implementation(project.dependencies.platform(libs.koinBom))
                implementation(libs.koin)
                implementation(libs.kermit)

                implementation(libs.okIo)
                implementation(libs.ktorClientCore)
                implementation(libs.ktorClientLogging)
                implementation(libs.ktorKotlinxSerialization)
                implementation(libs.ktorClientContentNegotiation)

                implementation(libs.apolloRuntime)
                implementation(libs.apolloAdapters)

                implementation(libs.supabaseGotrue)
                implementation(libs.supabasePostgrest)
                implementation(libs.supabaseRealtime)

                implementation(libs.multiplatformSettingsNoArg)
                implementation(libs.multiplatformSettingsCoroutines)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktorClientOkHttp)
                implementation(libs.okHttpLoggingInterceptor)
                implementation(libs.firebaseRemoteConfig)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktorClientDarwin)
            }
        }
    }
}

apollo {
    service("api") {
        // GraphQL configuration here.
        // https://www.apollographql.com/docs/kotlin/advanced/plugin-configuration/
        packageName.set("club.nito.core.network.graphql")

        // https://www.apollographql.com/docs/kotlin/essentials/custom-scalars/#in-buildgradlekts
        mapScalarToKotlinString("uuid")
        mapScalar("timestamptz", "kotlinx.datetime.Instant", "com.apollographql.apollo3.adapter.KotlinxInstantAdapter")

        // This will create a downloadStarwarsApolloSchemaFromIntrospection task
        val accessToken = getLocalProperty(key = "nito.hasura.accessToken")
        accessToken?.let {
            introspection {
                endpointUrl.set("https://nito-dev.hasura.app/v1/graphql")
                headers.set(
                    mapOf(
                        "Authorization" to "Bearer $it",
                    ),
                )
                // The path is interpreted relative to the current project here, no need to prepend 'app'
                schemaFile.set(file("src/commonMain/graphql/schema.graphqls"))
            }
        }
    }
}
