package club.nito.core.domain.di

import club.nito.core.domain.GetParticipantScheduleListExecutor
import club.nito.core.domain.GetParticipantScheduleListUseCase
import club.nito.core.domain.GetRecentScheduleExecutor
import club.nito.core.domain.GetRecentScheduleUseCase
import club.nito.core.domain.ModifyPasswordExecutor
import club.nito.core.domain.ModifyPasswordUseCase
import club.nito.core.domain.ObserveAuthStatusExecutor
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.domain.ParticipateExecutor
import club.nito.core.domain.ParticipateUseCase
import club.nito.core.domain.SignInExecutor
import club.nito.core.domain.SignInUseCase
import club.nito.core.domain.SignOutExecutor
import club.nito.core.domain.SignOutUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public val useCaseModule: Module = module {
    singleOf(::ObserveAuthStatusExecutor) bind ObserveAuthStatusUseCase::class
    singleOf(::SignInExecutor) bind SignInUseCase::class
    singleOf(::ModifyPasswordExecutor) bind ModifyPasswordUseCase::class
    singleOf(::SignOutExecutor) bind SignOutUseCase::class
    singleOf(::GetRecentScheduleExecutor) bind GetRecentScheduleUseCase::class
    singleOf(::GetParticipantScheduleListExecutor) bind GetParticipantScheduleListUseCase::class
    singleOf(::ParticipateExecutor) bind ParticipateUseCase::class
}
