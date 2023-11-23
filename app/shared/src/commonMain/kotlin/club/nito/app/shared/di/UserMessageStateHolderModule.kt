package club.nito.app.shared.di

import club.nito.core.ui.message.DefaultUserMessageStateHolder
import club.nito.core.ui.message.UserMessageStateHolder
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userMessageStateHolderModule = module {
    singleOf<UserMessageStateHolder>(::DefaultUserMessageStateHolder)
}
