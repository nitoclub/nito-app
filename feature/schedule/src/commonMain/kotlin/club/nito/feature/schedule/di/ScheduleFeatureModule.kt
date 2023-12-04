package club.nito.feature.schedule.di

import club.nito.feature.schedule.list.ScheduleListStateMachine
import org.koin.core.module.Module
import org.koin.dsl.module

public val scheduleFeatureModule: Module = module {
    factory {
        ScheduleListStateMachine(
            getParticipantScheduleList = get(),
            userMessageStateHolder = get(),
            dateTimeFormatter = get(),
        )
    }
}
