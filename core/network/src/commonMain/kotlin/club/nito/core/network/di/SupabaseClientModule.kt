package club.nito.core.network.di

import club.nito.core.network.createNitoKtorJsonSettings
import club.nito.core.network.createNitoSupabaseClient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

public val supabaseClientModule: Module = module {
    single<SupabaseClient> {
        createNitoSupabaseClient(
            json = get(),
        )
    }
    single<Json> {
        createNitoKtorJsonSettings()
    }
    single<Auth> {
        get<SupabaseClient>().auth
    }
}
