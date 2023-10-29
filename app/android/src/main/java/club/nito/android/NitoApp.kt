package club.nito.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.core.designsystem.theme.NitoTheme

@Composable
fun NitoApp(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    NitoTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            NitoNavHost(
                windowSize = windowSize,
            )
        }
    }
}
