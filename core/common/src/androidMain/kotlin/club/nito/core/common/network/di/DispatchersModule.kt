package club.nito.core.common.network.di

import club.nito.core.common.network.Dispatcher
import club.nito.core.common.network.NitoDispatchers
import club.nito.core.common.network.defaultDispatcher
import club.nito.core.common.network.ioDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(NitoDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = ioDispatcher

    @Provides
    @Dispatcher(NitoDispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = defaultDispatcher
}
