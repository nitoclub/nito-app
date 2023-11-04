package club.nito.core.network.di

import club.nito.core.common.network.di.ApplicationScope
import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.auth.SupabaseAuthRemoteDataSource
import club.nito.core.network.participation.ParticipantRemoteDataSource
import club.nito.core.network.participation.SupabaseParticipantRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import club.nito.core.network.schedule.SupabaseScheduleRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        @ApplicationScope coroutineScope: CoroutineScope,
        goTrue: GoTrue,
    ): AuthRemoteDataSource = SupabaseAuthRemoteDataSource(
        goTrue = goTrue,
    )

    @Provides
    @Singleton
    fun provideScheduleRemoteDataSource(
        client: SupabaseClient,
    ): ScheduleRemoteDataSource = SupabaseScheduleRemoteDataSource(
        client = client,
    )

    @Provides
    @Singleton
    fun provideParticipantRemoteDataSource(
        client: SupabaseClient,
    ): ParticipantRemoteDataSource = SupabaseParticipantRemoteDataSource(
        client = client,
    )
}
