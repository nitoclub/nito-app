package club.nito.core.datastore

import com.russhwolf.settings.Settings

internal fun createSettings() = try {
    Settings()
} catch (e: Exception) {
    error("Failed to create default settings")
}
