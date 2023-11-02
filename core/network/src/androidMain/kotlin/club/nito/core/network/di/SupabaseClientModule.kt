package club.nito.core.network.di

import club.nito.core.network.createNitoKtorJsonSettings
import club.nito.core.network.createNitoSupabaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SupabaseClientModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(json: Json): SupabaseClient = createNitoSupabaseClient(json)

    @Provides
    @Singleton
    fun provideKtorJsonSettings(): Json = createNitoKtorJsonSettings()

    @Provides
    @Singleton
    fun provideGoTrue(client: SupabaseClient): GoTrue = client.gotrue
}
