package club.nito.core.data.di

import club.nito.core.data.AuthRepository
import club.nito.core.data.DefaultAuthRepository
import club.nito.core.data.DefaultParticipationRepository
import club.nito.core.data.OfflineFirstScheduleRepository
import club.nito.core.data.ParticipationRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.participation.ParticipationRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource,
    ): AuthRepository = DefaultAuthRepository(
        remoteDataSource = remoteDataSource,
    )

    @Provides
    @Singleton
    fun provideScheduleRepository(
        remoteDataSource: ScheduleRemoteDataSource,
    ): ScheduleRepository = OfflineFirstScheduleRepository(
        remoteDataSource = remoteDataSource,
    )

    @Provides
    @Singleton
    fun provideParticipationRepository(
        remoteDataSource: ParticipationRemoteDataSource,
    ): ParticipationRepository = DefaultParticipationRepository(
        remoteDataSource = remoteDataSource,
    )
}
