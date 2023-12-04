package club.nito.core.datastore

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.serialization.json.Json

public class SettingsDataStore(
    private val settings: Settings,
    @OptIn(ExperimentalSettingsApi::class)
    private val suspendSettings: SuspendSettings = settings.toSuspendSettings(),
    private val json: Json,
) : DataStore
