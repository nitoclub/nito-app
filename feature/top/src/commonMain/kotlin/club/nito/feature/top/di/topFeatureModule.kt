package club.nito.feature.top.di

import club.nito.feature.top.TopStateMachine
import org.koin.core.module.Module
import org.koin.dsl.module

val topFeatureModule: Module = module {
    factory {
        TopStateMachine(
            getRecentSchedule = get(),
            userMessageStateHolder = get(),
            dateTimeFormatter = get(),
        )
    }
}
