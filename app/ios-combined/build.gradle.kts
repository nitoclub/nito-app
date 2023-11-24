import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("nito.primitive.kmp")
    id("nito.primitive.kmp.ios")
    id("nito.primitive.kmp.compose")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    val frameworkName = "NitoCombined"
    val xcf = XCFramework(frameworkName)

    targets.filterIsInstance<KotlinNativeTarget>()
        .forEach {
            it.binaries {
                framework {
                    baseName = frameworkName
                    // compose for iOS(skiko) needs to be static library
                    isStatic = true
                    embedBitcode(BitcodeEmbeddingMode.DISABLE)
                    binaryOption("bundleId", "club.nito.ios.combined")
                    binaryOption("bundleVersion", version.toString())
                    binaryOption("bundleShortVersionString", version.toString())
                    xcf.add(this)

                    export(projects.core.model)
                    export(projects.core.domain)
                    export(projects.core.data)
                    export(projects.core.ui)

                    export(projects.feature.top)
                    export(projects.feature.auth)
                    export(projects.feature.schedule)
                    export(projects.feature.settings)

                    export(compose.ui)
                }
            }
        }

    sourceSets {
        commonMain {
            dependencies {
                api(projects.app.shared)

                api(projects.core.model)
                api(projects.core.domain)
                api(projects.core.data)
                api(projects.core.ui)

                api(projects.feature.top)
                api(projects.feature.auth)
                api(projects.feature.schedule)
                api(projects.feature.settings)

                api(compose.ui)

                implementation(libs.kermitKoin)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        iosMain {
            dependencies {
                implementation(libs.koin)
            }
        }
    }
}
