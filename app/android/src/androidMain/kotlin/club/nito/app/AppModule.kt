package club.nito.app

import club.nito.core.model.BuildConfig
import org.koin.dsl.module
import club.nito.app.BuildConfig as AppBuildConfig

val appModule = module {
    single<BuildConfig> {
        object : BuildConfig {
            override val versionName: String = AppBuildConfig.VERSION_NAME
            override val debugBuild: Boolean = AppBuildConfig.DEBUG
        }
    }
}
