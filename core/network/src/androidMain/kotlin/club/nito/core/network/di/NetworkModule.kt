package club.nito.core.network.di

import club.nito.core.network.schedule.FakeScheduleRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideScheduleRemoteDataSource(): ScheduleRemoteDataSource = FakeScheduleRemoteDataSource
}
