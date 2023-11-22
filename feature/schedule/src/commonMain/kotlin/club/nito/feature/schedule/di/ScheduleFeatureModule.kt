package club.nito.feature.schedule.di

import club.nito.feature.schedule.ScheduleListViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleFeatureModule: Module = module {
    factory {
        ScheduleListViewModel(
            getParticipantScheduleListUseCase = get(),
            userMessageStateHolder = get(),
            dateTimeFormatter = get(),
        )
    }
}
