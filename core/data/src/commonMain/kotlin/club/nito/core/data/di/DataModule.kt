package club.nito.core.data.di

import club.nito.core.data.AuthRepository
import club.nito.core.data.DefaultAuthRepository
import club.nito.core.data.DefaultParticipantRepository
import club.nito.core.data.DefaultUserRepository
import club.nito.core.data.OfflineFirstScheduleRepository
import club.nito.core.data.ParticipantRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.data.UserRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public val dataModule: Module = module {
    singleOf(::DefaultAuthRepository) bind AuthRepository::class
    singleOf(::OfflineFirstScheduleRepository) bind ScheduleRepository::class
    singleOf(::DefaultParticipantRepository) bind ParticipantRepository::class
    singleOf(::DefaultUserRepository) bind UserRepository::class
}
