package club.nito.android

import android.util.Log
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import club.nito.core.model.AuthStatus
import club.nito.feature.auth.navigateToSignIn
import club.nito.feature.auth.signInNavigationRoute
import club.nito.feature.auth.signInScreen
import club.nito.feature.schedule.navigateToSchedule
import club.nito.feature.schedule.scheduleScreen
import club.nito.feature.settings.navigateToSettings
import club.nito.feature.settings.settingsScreen
import club.nito.feature.top.navigateToTop
import club.nito.feature.top.topNavigationRoute
import club.nito.feature.top.topScreen

const val rootNavigationRoute = "root_route"

@Composable
fun NitoNavHost(
    windowSize: WindowSizeClass,
    authStatus: AuthStatus,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = rootNavigationRoute,
) {
    NavHostWithSharedAxisX(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        root(
            authStatus = authStatus,
            navController = navController,
        )

        topScreen(
            onScheduleListClick = navController::navigateToSchedule,
            onSettingsClick = navController::navigateToSettings,
        )
        signInScreen(
            onSignedIn = {
                navController.navigateToTop(
                    navOptions = navOptions {
                        popUpTo(signInNavigationRoute) {
                            inclusive = true
                        }
                    },
                )
            },
        )
        scheduleScreen()
        settingsScreen(
            onSignedOut = {
                navController.navigateToSignIn(
                    navOptions = navOptions {
                        popUpTo(topNavigationRoute) {
                            inclusive = true
                        }
                    },
                )
            },
        )
    }
}

private fun NavGraphBuilder.root(
    authStatus: AuthStatus,
    navController: NavHostController,
) = composable(rootNavigationRoute) {
    LaunchedEffect(authStatus) {
        Log.e("authStatus", authStatus.toString())

        when (authStatus) {
            AuthStatus.NotAuthenticated -> navController.navigateToSignIn(
                navOptions = navOptions {
                    popUpTo(rootNavigationRoute) {
                        inclusive = true
                    }
                },
            )

            is AuthStatus.Authenticated -> navController.navigateToTop(
                navOptions = navOptions {
                    popUpTo(rootNavigationRoute) {
                        inclusive = true
                    }
                },
            )

            else -> {}
        }
    }
}
