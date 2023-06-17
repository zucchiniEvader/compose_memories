import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.theme.AppTheme
import pages.HomePage

@Composable
fun App() {
    AppTheme(
    ) {
        Surface (modifier = Modifier.fillMaxSize()
            , color = MaterialTheme.colorScheme.background
        ) {
            HomePage()
        }
    }
}

expect fun getPlatformName(): String
