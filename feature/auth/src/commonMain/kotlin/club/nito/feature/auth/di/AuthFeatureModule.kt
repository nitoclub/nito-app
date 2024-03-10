package club.nito.feature.auth.di

import club.nito.feature.auth.login.LoginScreenStateMachine
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

public val authFeatureModule: Module = module {
    factoryOf(::LoginScreenStateMachine)
}
