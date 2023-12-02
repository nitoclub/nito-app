package club.nito.core.domain.di

import club.nito.core.domain.AuthStatusStreamExecutor
import club.nito.core.domain.AuthStatusStreamUseCase
import club.nito.core.domain.GetParticipantScheduleListExecutor
import club.nito.core.domain.GetParticipantScheduleListUseCase
import club.nito.core.domain.GetRecentScheduleExecutor
import club.nito.core.domain.GetRecentScheduleUseCase
import club.nito.core.domain.LoginExecutor
import club.nito.core.domain.LoginUseCase
import club.nito.core.domain.LogoutExecutor
import club.nito.core.domain.LogoutUseCase
import club.nito.core.domain.ModifyPasswordExecutor
import club.nito.core.domain.ModifyPasswordUseCase
import club.nito.core.domain.ParticipateExecutor
import club.nito.core.domain.ParticipateUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public val useCaseModule: Module = module {
    singleOf(::AuthStatusStreamExecutor) bind AuthStatusStreamUseCase::class
    singleOf(::LoginExecutor) bind LoginUseCase::class
    singleOf(::ModifyPasswordExecutor) bind ModifyPasswordUseCase::class
    singleOf(::LogoutExecutor) bind LogoutUseCase::class
    singleOf(::GetRecentScheduleExecutor) bind GetRecentScheduleUseCase::class
    singleOf(::GetParticipantScheduleListExecutor) bind GetParticipantScheduleListUseCase::class
    singleOf(::ParticipateExecutor) bind ParticipateUseCase::class
}
