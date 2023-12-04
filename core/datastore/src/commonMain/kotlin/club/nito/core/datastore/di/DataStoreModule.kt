package club.nito.core.datastore.di

import club.nito.core.common.nitoJsonSettings
import club.nito.core.datastore.DataStore
import club.nito.core.datastore.SettingsDataStore
import club.nito.core.datastore.createSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.module.Module
import org.koin.dsl.module

public val dataStoreModule: Module = module {
    single<DataStore> {
        @OptIn(ExperimentalSettingsApi::class)
        SettingsDataStore(
            settings = createSettings(),
            json = nitoJsonSettings
        )
    }
}
