package club.nito.core.network

import club.nito.core.common.nitoJsonSettings
import co.touchlab.kermit.Logger
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.toSuspendSettings
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionManager
import io.github.jan.supabase.gotrue.user.UserSession
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun createNitoSupabaseClient(
    json: Json,
): SupabaseClient = createSupabaseClient(
    supabaseUrl = "https://gtfjukrauyhrbglrzlva.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd0Zmp1a3JhdXlocmJnbHJ6bHZhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTkwMDQ3NTgsImV4cCI6MjAxNDU4MDc1OH0.zRjlzXVyz4vBM8Tb8GcpyPyTkCmOwdV-Xs18Agw2w-E",
) {
    install(Postgrest)
    install(Auth) {
        sessionManager = NitoSettingsSessionManager()
    }
    install(Realtime)

    defaultSerializer = KotlinXSerializer(json)
}

/**
 * Supabase の [SessionManager] を [Settings] で実装したもの
 *
 * NOTE: Supabase の [io.github.jan.supabase.gotrue.SettingsSessionManager] が正常に動作しなかったため
 */
private class NitoSettingsSessionManager(settings: Settings = Settings()) : SessionManager {
    @OptIn(ExperimentalSettingsApi::class)
    private val suspendSettings = settings.toSuspendSettings()

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun saveSession(session: UserSession) {
        suspendSettings.putString(SETTINGS_KEY, nitoJsonSettings.encodeToString(session))
    }

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun loadSession(): UserSession? {
        val session = suspendSettings.getStringOrNull(SETTINGS_KEY) ?: return null
        return try {
            nitoJsonSettings.decodeFromString(session)
        } catch (e: Exception) {
            Logger.e(e) { "Failed to load session" }
            null
        }
    }

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun deleteSession(): Unit = suspendSettings.remove(SETTINGS_KEY)

    companion object {
        const val SETTINGS_KEY = "session"
    }
}
