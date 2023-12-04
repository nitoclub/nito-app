package club.nito.core.datastore

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSettingsApi::class)
public class SettingsDataStore(
    private val settings: Settings,
    @OptIn(ExperimentalSettingsApi::class)
    private val suspendSettings: SuspendSettings = settings.toSuspendSettings(),
    private val json: Json,
) : DataStore {
    override suspend fun setRefreshed(isRefreshed: Boolean) {
        suspendSettings.putBoolean(REFRESHED_KEY, isRefreshed)
    }

    override suspend fun isRefreshed(): Boolean = suspendSettings.getBoolean(REFRESHED_KEY, false)

    public companion object {
        private const val REFRESHED_KEY = "refreshed-v0-3-3"
    }
}
