package club.nito.core.domain.di

import club.nito.core.data.AuthRepository
import club.nito.core.data.ParticipantRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.data.UserRepository
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideObserveAuthStatusUseCase(
        authRepository: AuthRepository,
    ): ObserveAuthStatusUseCase = ObserveAuthStatusExecutor(
        authRepository = authRepository,
    )

    @Provides
    fun provideSignInUseCase(
        authRepository: AuthRepository,
    ): SignInUseCase = SignInExecutor(
        authRepository = authRepository,
    )

    @Provides
    fun provideModifyPasswordUseCase(
        authRepository: AuthRepository,
    ): ModifyPasswordUseCase = ModifyPasswordExecutor(
        authRepository = authRepository,
    )

    @Provides
    fun provideSignOutUseCase(
        authRepository: AuthRepository,
    ): SignOutUseCase = SignOutExecutor(
        authRepository = authRepository,
    )

    @Provides
    fun provideGetRecentScheduleUseCase(
        scheduleRepository: ScheduleRepository,
        participantRepository: ParticipantRepository,
        userRepository: UserRepository,
    ): GetRecentScheduleUseCase = GetRecentScheduleExecutor(
        scheduleRepository = scheduleRepository,
        participantRepository = participantRepository,
        userRepository = userRepository,
    )

    @Provides
    fun provideGetParticipantScheduleListUseCase(
        scheduleRepository: ScheduleRepository,
        participantRepository: ParticipantRepository,
        userRepository: UserRepository,
    ): GetParticipantScheduleListUseCase = GetParticipantScheduleListExecutor(
        scheduleRepository = scheduleRepository,
        participantRepository = participantRepository,
        userRepository = userRepository,
    )

    @Provides
    fun provideParticipateUseCase(
        participantRepository: ParticipantRepository,
    ): ParticipateUseCase = ParticipateExecutor(
        participantRepository = participantRepository,
    )
}
