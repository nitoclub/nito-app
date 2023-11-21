package club.nito.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.app.di.featureModules
import club.nito.app.di.nitoDateTimeFormatterModule
import club.nito.app.di.userMessageStateHolderModule
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
    windowSize: WindowSizeClass,
    authStatus: AuthStatus,
    modifier: Modifier = Modifier,
) {
    KoinApplication(
        application = {
            modules(
                nitoDateTimeFormatterModule,
                userMessageStateHolderModule,

                supabaseClientModule,
                remoteDataSourceModule,
//                fakeRemoteDataSourceModule,
                dataModule,
                useCaseModule,
                topFeatureModule,
            )

            modules(featureModules)

//            val kermit = Logger.withTag("koin")
//            logger(KermitKoinLogger(kermit))
        },
    ) {
        NitoTheme {
            Surface(
                modifier = modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                NitoNavHost(
                    windowSize = windowSize,
                    authStatus = authStatus,
                )
            }
        }
    }
}
