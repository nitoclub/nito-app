package club.nito.ios.combined

import club.nito.core.data.dataModule
import club.nito.core.domain.useCaseModule
import club.nito.core.network.remoteDataSourceModule
import club.nito.core.network.supabaseClientModule
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import kotlin.reflect.KClass

class KmpEntryPoint {
    lateinit var koinApplication: KoinApplication

    fun init() {
        koinApplication = startKoin {
            modules(
                supabaseClientModule,
                remoteDataSourceModule,
//                fakeRemoteDataSourceModule,
                dataModule,
                useCaseModule,
            )

            val kermit = Logger.withTag("koin")
            logger(KermitKoinLogger(kermit))
        }
    }

    inline fun <reified T : Any> get(): T {
        return koinApplication.koin.get(T::class)
    }

    fun <T : Any> get(clazz: KClass<T>): T {
        return koinApplication.koin.get(clazz)
    }
}
