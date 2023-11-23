package club.nito.ios.combined

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
                supabaseClientModule,
                remoteDataSourceModule,
//                fakeRemoteDataSourceModule,
                dataModule,
                useCaseModule,
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
