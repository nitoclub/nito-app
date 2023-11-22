package club.nito.app.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.core.data.di.dataModule
import club.nito.core.designsystem.theme.NitoTheme
import club.nito.core.domain.di.useCaseModule
import club.nito.core.model.AuthStatus
import club.nito.core.network.di.remoteDataSourceModule
import club.nito.core.network.di.supabaseClientModule
import club.nito.feature.top.di.topFeatureModule
import org.koin.compose.KoinApplication

@Composable
fun NitoApp(
    authStatus: AuthStatus,
    modifier: Modifier = Modifier,
) {
    KoinApplication(
        application = {
            modules(
                supabaseClientModule,
                remoteDataSourceModule,
                dataModule,
                useCaseModule,
                topFeatureModule,
            )
        },
    ) {
        NitoTheme {
            Surface(
                modifier = modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                NitoNavHost(
                    authStatus = authStatus,
                )
            }
        }
    }
}
