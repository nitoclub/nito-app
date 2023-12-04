package club.nito.core.common

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

public val nitoJsonSettings: Json = Json {
    encodeDefaults = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    allowStructuredMapKeys = true
    prettyPrint = false
    useArrayPolymorphism = false
    ignoreUnknownKeys = true
    coerceInputValues = true
    useAlternativeNames = false

    @OptIn(ExperimentalSerializationApi::class)
    namingStrategy = JsonNamingStrategy.SnakeCase
}
