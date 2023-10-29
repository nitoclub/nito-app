package club.nito.core.domain.di

import club.nito.core.data.ScheduleRepository
import club.nito.core.domain.GetRecentScheduleExecutor
import club.nito.core.domain.GetRecentScheduleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideGetRecentScheduleUseCase(
        scheduleRepository: ScheduleRepository,
    ): GetRecentScheduleUseCase = GetRecentScheduleExecutor(
        scheduleRepository = scheduleRepository,
    )
}
