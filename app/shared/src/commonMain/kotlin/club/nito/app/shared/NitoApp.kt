package club.nito.app.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import club.nito.app.shared.di.featureModules
import club.nito.app.shared.di.nitoDateTimeFormatterModule
import club.nito.app.shared.di.userMessageStateHolderModule
import club.nito.core.data.di.dataModule
import club.nito.core.designsystem.theme.NitoTheme
import club.nito.core.domain.di.useCaseModule
import club.nito.core.network.di.remoteDataSourceModule
import club.nito.core.network.di.supabaseClientModule
import club.nito.core.ui.koinStateMachine
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun NitoApp(
    shouldKeepOnScreen: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    PreComposeApp {
        KoinApplication(
            application = {
                logger(
                    KermitKoinLogger(Logger.withTag("koin")),
                )

                modules(
                    nitoDateTimeFormatterModule,
                    userMessageStateHolderModule,

                    supabaseClientModule,
                    remoteDataSourceModule,
//                fakeRemoteDataSourceModule,
                    dataModule,
                    useCaseModule,
                )

                modules(
                    module {
                        factory {
                            NitoAppStateMachine(
                                observeAuthStatus = get(),
                            )
                        }
                    },
                )

                modules(featureModules)

//            val kermit = Logger.withTag("koin")
//            logger(KermitKoinLogger(kermit))
            },
        ) {
            val stateMachine = koinStateMachine<NitoAppStateMachine>()
            val uiState = stateMachine.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(uiState.value) {
                shouldKeepOnScreen((uiState.value is NitoAppUiState.Success).not())
            }

            when (val state = uiState.value) {
                NitoAppUiState.Loading -> {}
                is NitoAppUiState.Success -> NitoTheme {
                    Surface(
                        modifier = modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        NitoNavHost(
                            authStatus = state.authStatus,
                        )
                    }
                }
            }
        }
    }
}
