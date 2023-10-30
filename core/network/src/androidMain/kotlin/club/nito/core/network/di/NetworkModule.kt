package club.nito.core.network.di

import club.nito.core.common.network.di.ApplicationScope
import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.auth.FakeAuthRemoteDataSource
import club.nito.core.network.schedule.FakeScheduleRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        @ApplicationScope coroutineScope: CoroutineScope,
    ): AuthRemoteDataSource = FakeAuthRemoteDataSource(
        coroutineScope = coroutineScope,
    )

    @Provides
    @Singleton
    fun provideScheduleRemoteDataSource(): ScheduleRemoteDataSource = FakeScheduleRemoteDataSource
}
