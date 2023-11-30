package club.nito.core.network

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

internal fun createNitoSupabaseClient(
    json: Json,
): SupabaseClient = createSupabaseClient(
    supabaseUrl = "https://gtfjukrauyhrbglrzlva.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd0Zmp1a3JhdXlocmJnbHJ6bHZhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTkwMDQ3NTgsImV4cCI6MjAxNDU4MDc1OH0.zRjlzXVyz4vBM8Tb8GcpyPyTkCmOwdV-Xs18Agw2w-E",
) {
    install(Postgrest)
    install(Auth)
    install(Realtime)

    defaultSerializer = KotlinXSerializer(json)
}

@OptIn(ExperimentalSerializationApi::class)
internal fun createNitoKtorJsonSettings(): Json = Json {
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
