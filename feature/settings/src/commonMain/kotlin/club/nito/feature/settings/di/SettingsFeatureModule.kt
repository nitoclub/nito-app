package club.nito.feature.settings.di

import club.nito.feature.settings.SettingsScreenStateMachine
import org.koin.core.module.Module
import org.koin.dsl.module

val settingsFeatureModule: Module = module {
    factory {
        SettingsScreenStateMachine(
            observeAuthStatus = get(),
            modifyPassword = get(),
            signOut = get(),
            userMessageStateHolder = get(),
        )
    }
}
