package club.nito.app.di

import club.nito.core.ui.message.DefaultUserMessageStateHolder
import club.nito.core.ui.message.UserMessageStateHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@Module
@InstallIn(SingletonComponent::class)
class UserMessageStateHolderModule {
    @Provides
    fun provideUserMessageStateHolder(): UserMessageStateHolder = DefaultUserMessageStateHolder()
}

val userMessageStateHolderModule = module {
    singleOf<UserMessageStateHolder>(::DefaultUserMessageStateHolder)
}
