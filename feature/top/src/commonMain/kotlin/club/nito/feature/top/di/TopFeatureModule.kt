package club.nito.feature.top.di

import club.nito.feature.top.TopScreenStateMachine
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

public val topFeatureModule: Module = module {
    factoryOf(::TopScreenStateMachine)
}
