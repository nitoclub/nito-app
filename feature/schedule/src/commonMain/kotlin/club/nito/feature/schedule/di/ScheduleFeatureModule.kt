package club.nito.feature.schedule.di

import club.nito.core.model.schedule.ScheduleId
import club.nito.feature.schedule.detail.ScheduleDetailStateMachine
import club.nito.feature.schedule.list.ScheduleListStateMachine
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

public val scheduleFeatureModule: Module = module {
    factoryOf(::ScheduleListStateMachine)

    factory { (scheduleId: ScheduleId) ->
        ScheduleDetailStateMachine(
            scheduleId = scheduleId,
            scheduleStream = get(),
            scheduleParticipantsStream = get(),
            myParticipantStatusStream = get(),
            participate = get(),
            userMessageStateHolder = get(),
            dateTimeFormatter = get(),
        )
    }
}
