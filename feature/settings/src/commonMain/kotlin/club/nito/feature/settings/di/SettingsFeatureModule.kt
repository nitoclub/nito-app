package club.nito.feature.settings.di

import club.nito.feature.settings.SettingsScreenStateMachine
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

public val settingsFeatureModule: Module = module {
    factoryOf(::SettingsScreenStateMachine)
}
