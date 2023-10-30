package club.nito.android.di

import club.nito.core.ui.message.DefaultUserMessageStateHolder
import club.nito.core.ui.message.UserMessageStateHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserMessageStateHolderModule {
    @Provides
    fun provideUserMessageStateHolder(): UserMessageStateHolder = DefaultUserMessageStateHolder()
}
