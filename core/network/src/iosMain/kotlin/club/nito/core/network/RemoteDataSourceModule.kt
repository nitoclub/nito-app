package club.nito.core.network

import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.auth.SupabaseAuthRemoteDataSource
import club.nito.core.network.participation.ParticipationRemoteDataSource
import club.nito.core.network.participation.SupabaseParticipationRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import club.nito.core.network.schedule.SupabaseScheduleRemoteDataSource
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
    single<ParticipationRemoteDataSource> {
        SupabaseParticipationRemoteDataSource(
            client = get(),
        )
    }
}
