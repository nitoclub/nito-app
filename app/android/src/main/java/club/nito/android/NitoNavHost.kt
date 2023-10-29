package club.nito.android

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import club.nito.feature.auth.authScreen
import club.nito.feature.auth.navigateToAuth
import club.nito.feature.schedule.navigateToSchedule
import club.nito.feature.schedule.scheduleScreen
import club.nito.feature.settings.navigateToSettings
import club.nito.feature.settings.settingsScreen
import club.nito.feature.top.navigateToTop
import club.nito.feature.top.topNavigationRoute
import club.nito.feature.top.topScreen

@Composable
fun NitoNavHost(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = topNavigationRoute,
) {
    NavHostWithSharedAxisX(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        topScreen(
            onScheduleClick = navController::navigateToSchedule,
            onSettingsClick = navController::navigateToSettings,
        )
        authScreen(
            onSignInClick = navController::navigateToTop,
        )
        scheduleScreen()
        settingsScreen(
            onSignOutClick = navController::navigateToAuth,
        )
    }
}
