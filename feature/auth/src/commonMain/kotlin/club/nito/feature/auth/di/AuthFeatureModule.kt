package club.nito.feature.auth.di

import club.nito.feature.auth.LoginScreenStateMachine
import org.koin.core.module.Module
import org.koin.dsl.module

public val authFeatureModule: Module = module {
    factory {
        LoginScreenStateMachine(
            observeAuthStatusUseCase = get(),
            login = get(),
            userMessageStateHolder = get(),
        )
    }
}
