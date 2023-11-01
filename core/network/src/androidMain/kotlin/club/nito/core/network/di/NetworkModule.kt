package club.nito.core.network.di

import club.nito.core.common.network.di.ApplicationScope
import club.nito.core.network.InstantSerializer
import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.auth.SupabaseAuthRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import club.nito.core.network.schedule.SupabaseScheduleRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(
        json: Json,
    ): SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://hwxxihvcszfhaxlguajv.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh3eHhpaHZjc3pmaGF4bGd1YWp2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2NDcwODc3MjYsImV4cCI6MTk2MjY2MzcyNn0.ieij2I1mYfKsS70tv5h8Kzudcrv0YeCVG38ld9AwlSQ",
    ) {
        install(Postgrest)
        install(GoTrue)
        install(Realtime)

        defaultSerializer = KotlinXSerializer(json)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideKtorJsonSettings(): Json = Json {
        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = false
        useArrayPolymorphism = false
        ignoreUnknownKeys = true
        coerceInputValues = true
        useAlternativeNames = false

        namingStrategy = JsonNamingStrategy.SnakeCase

        serializersModule = SerializersModule {
            contextual(InstantSerializer)
        }
    }

    @Provides
    @Singleton
    fun provideGoTrue(client: SupabaseClient): GoTrue = client.gotrue

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        @ApplicationScope coroutineScope: CoroutineScope,
        goTrue: GoTrue,
    ): AuthRemoteDataSource = SupabaseAuthRemoteDataSource(
        goTrue = goTrue,
    )

    @Provides
    @Singleton
    fun provideScheduleRemoteDataSource(
        client: SupabaseClient,
    ): ScheduleRemoteDataSource = SupabaseScheduleRemoteDataSource(
        client = client,
    )
}
