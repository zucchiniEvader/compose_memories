import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.rememberWindowInsetsController
import com.moriatsushi.insetsx.safeArea
import com.moriatsushi.insetsx.safeDrawing
import com.moriatsushi.insetsx.statusBars
import ui.theme.AppTheme
import pages.HomePage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val windowInsetsController = rememberWindowInsetsController()
    val isDarkTheme = isSystemInDarkTheme()
    LaunchedEffect(Unit) {
        // The status bars icon + content will change to a light color
        windowInsetsController?.setStatusBarContentColor(dark = !isDarkTheme)
        // The navigation bars icons will change to a light color (android only)
        windowInsetsController?.setNavigationBarsContentColor(dark = !isDarkTheme)
    }

    AppTheme(
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
                .windowInsetsPadding(
                    WindowInsets.safeArea.only(WindowInsetsSides.Top)
                )
                .padding(horizontal = 8.dp),
        ) {
            HomePage()
        }

    }
}

expect fun getPlatformName(): String
