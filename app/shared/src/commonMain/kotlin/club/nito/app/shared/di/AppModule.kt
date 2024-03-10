package club.nito.app.shared.di

import club.nito.app.shared.NitoAppStateMachine
import club.nito.core.common.NitoCoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::NitoAppStateMachine)

    single {
        @OptIn(ExperimentalCoroutinesApi::class)
        NitoCoroutineDispatchers(
            io = Dispatchers.IO,
            databaseWrite = Dispatchers.IO.limitedParallelism(1),
            databaseRead = Dispatchers.IO.limitedParallelism(4),
            computation = Dispatchers.Default,
            main = Dispatchers.Main,
        )
    }
}
