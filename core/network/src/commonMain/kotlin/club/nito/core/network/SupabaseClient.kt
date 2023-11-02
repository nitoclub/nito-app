package club.nito.core.network

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
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
    supabaseUrl = "https://hwxxihvcszfhaxlguajv.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh3eHhpaHZjc3pmaGF4bGd1YWp2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2NDcwODc3MjYsImV4cCI6MTk2MjY2MzcyNn0.ieij2I1mYfKsS70tv5h8Kzudcrv0YeCVG38ld9AwlSQ",
) {
    install(Postgrest)
    install(GoTrue)
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
