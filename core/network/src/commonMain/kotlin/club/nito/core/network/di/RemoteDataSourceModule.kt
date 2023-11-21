package club.nito.core.network.di

import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.auth.SupabaseAuthRemoteDataSource
import club.nito.core.network.participation.ParticipantRemoteDataSource
import club.nito.core.network.participation.SupabaseParticipantRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import club.nito.core.network.schedule.SupabaseScheduleRemoteDataSource
import club.nito.core.network.user.SupabaseUserRemoteDataSource
import club.nito.core.network.user.UserRemoteDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteDataSourceModule: Module = module {
    single<AuthRemoteDataSource> {
        SupabaseAuthRemoteDataSource(
            goTrue = get(),
        )
    }
    single<ScheduleRemoteDataSource> {
        SupabaseScheduleRemoteDataSource(
            client = get(),
        )
    }
    single<ParticipantRemoteDataSource> {
        SupabaseParticipantRemoteDataSource(
            client = get(),
        )
    }
    single<UserRemoteDataSource> {
        SupabaseUserRemoteDataSource(
            client = get(),
        )
    }
}
