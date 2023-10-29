package club.nito.android

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import club.nito.feature.auth.authScreen
import club.nito.feature.schedule.scheduleScreen
import club.nito.feature.settings.settingsScreen
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
        topScreen()
        authScreen()
        scheduleScreen()
        settingsScreen()
    }
}
