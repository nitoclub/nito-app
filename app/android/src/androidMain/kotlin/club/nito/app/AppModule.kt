package club.nito.app

import club.nito.core.model.BuildConfig
import club.nito.core.model.Flavor
import org.koin.dsl.module
import club.nito.app.BuildConfig as AppBuildConfig

val appModule = module {
    single<BuildConfig> {
        object : BuildConfig {
            override val applicationId: String = AppBuildConfig.APPLICATION_ID
            override val versionName: String = AppBuildConfig.VERSION_NAME
            override val debugBuild: Boolean = AppBuildConfig.DEBUG
            override val flavor: Flavor = when (AppBuildConfig.FLAVOR) {
                "dev" -> Flavor.Dev
                "prod" -> Flavor.Prod
                else -> Flavor.Dev
            }
        }
    }
}
