package club.nito.core.network

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

val supabaseClientModule: Module = module {
    single<SupabaseClient> {
        createNitoSupabaseClient(
            json = get(),
        )
    }
    single<Json> {
        createNitoKtorJsonSettings()
    }
    single<GoTrue> {
        get<SupabaseClient>().gotrue
    }
}
