package club.nito.ios.combined

import club.nito.app.shared.di.appModule
import club.nito.app.shared.di.featureModules
import club.nito.app.shared.di.nitoDateFormatterModule
import club.nito.app.shared.di.userMessageStateHolderModule
import club.nito.core.data.di.dataModule
import club.nito.core.datastore.di.dataStoreModule
import club.nito.core.domain.di.useCaseModule
import club.nito.core.model.BuildConfig
import club.nito.core.network.di.remoteDataSourceModule
import club.nito.core.network.di.supabaseClientModule
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.reflect.KClass

class KmpEntryPoint {
    lateinit var koinApplication: KoinApplication

    fun init(
        buildConfig: BuildConfig = BuildConfig.Empty,
    ) {
        koinApplication = startKoin {
            logger(
                KermitKoinLogger(Logger.withTag("koin")),
            )

            modules(
                nitoDateFormatterModule,
                userMessageStateHolderModule,

                supabaseClientModule,
                remoteDataSourceModule,
//                fakeRemoteDataSourceModule,
                dataStoreModule,
                dataModule,
                useCaseModule,

                appModule,
                *featureModules.toTypedArray(),

                module {
                    single<BuildConfig> { buildConfig }
                },
            )
        }
    }

    inline fun <reified T : Any> get(): T {
        return koinApplication.koin.get(T::class)
    }

    fun <T : Any> get(clazz: KClass<T>): T {
        return koinApplication.koin.get(clazz)
    }
}
