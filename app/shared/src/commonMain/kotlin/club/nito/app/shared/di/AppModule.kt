package club.nito.app.shared.di

import club.nito.app.shared.NitoAppStateMachine
import org.koin.dsl.module

val appModule = module {
    factory {
        NitoAppStateMachine(
            authStatusStream = get(),
        )
    }
}
