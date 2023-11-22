package club.nito.app

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
import club.nito.feature.auth.loginNavigationRoute
import club.nito.feature.auth.loginScreen
import club.nito.feature.auth.navigateToLogin
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
        loginScreen(
            onLoggedIn = {
                navController.navigateToTop(
                    navOptions = navOptions {
                        popUpTo(loginNavigationRoute) {
                            inclusive = true
                        }
                    },
                )
            },
        )
        scheduleScreen()
        settingsScreen(
            onSignedOut = {
                navController.navigateToLogin(
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
        when (authStatus) {
            AuthStatus.NotAuthenticated -> navController.navigateToLogin(
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
        }
    }
}
