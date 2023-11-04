import com.expediagroup.graphql.plugin.gradle.config.GraphQLSerializer

plugins {
    kotlin("jvm")
    id("io.ktor.plugin") version libs.versions.ktor
    id("org.jetbrains.kotlin.plugin.serialization") version libs.versions.kotlin
    id("com.expediagroup.graphql") version libs.versions.graphqlKotlin
}

group = "club.nito"
version = "0.1.0"

application {
    mainClass.set("nito.club.bff.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(libs.ktorServerCore)
    implementation(libs.ktorServerNetty)
    implementation(libs.ktorServerCors)
    implementation(libs.ktorServerWebsockets)
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation(libs.logback)
    implementation(libs.graphqlKotlinKtorServer)
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation(kotlin("test"))
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

graphql {
    schema {
        packages = listOf("nito.club.bff")
    }
    client {
        serializer = GraphQLSerializer.KOTLINX
    }
}
