package club.nito.core.network.di

import club.nito.core.network.NetworkService
import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.auth.SupabaseAuthRemoteDataSource
import club.nito.core.network.participation.ParticipantRemoteDataSource
import club.nito.core.network.participation.SupabaseParticipantRemoteDataSource
import club.nito.core.network.place.PlaceRemoteDataSource
import club.nito.core.network.place.SupabasePlaceRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import club.nito.core.network.schedule.SupabaseScheduleRemoteDataSource
import club.nito.core.network.user.SupabaseUserRemoteDataSource
import club.nito.core.network.user.UserRemoteDataSource
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public val remoteDataSourceModule: Module = module {
    singleOf(::NetworkService)
    singleOf(::SupabaseAuthRemoteDataSource) bind AuthRemoteDataSource::class
    singleOf(::SupabaseScheduleRemoteDataSource) bind ScheduleRemoteDataSource::class
    singleOf(::SupabaseParticipantRemoteDataSource) bind ParticipantRemoteDataSource::class
    singleOf(::SupabaseUserRemoteDataSource) bind UserRemoteDataSource::class
    singleOf(::SupabasePlaceRemoteDataSource) bind PlaceRemoteDataSource::class
}
