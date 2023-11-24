package club.nito.ios.combined

import club.nito.app.shared.di.appModule
import club.nito.app.shared.di.featureModules
import club.nito.app.shared.di.nitoDateTimeFormatterModule
import club.nito.app.shared.di.userMessageStateHolderModule
import club.nito.core.data.di.dataModule
import club.nito.core.domain.di.useCaseModule
import club.nito.core.network.di.remoteDataSourceModule
import club.nito.core.network.di.supabaseClientModule
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import kotlin.reflect.KClass

class KmpEntryPoint {
    lateinit var koinApplication: KoinApplication

    fun init() {
        koinApplication = startKoin {
            logger(
                KermitKoinLogger(Logger.withTag("koin")),
            )

            modules(
                nitoDateTimeFormatterModule,
                userMessageStateHolderModule,

                supabaseClientModule,
                remoteDataSourceModule,
//                fakeRemoteDataSourceModule,
                dataModule,
                useCaseModule,

                appModule,
                *featureModules.toTypedArray(),
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
