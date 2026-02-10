package theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme

/**
 * AppTheme wraps MaterialTheme and builds a color scheme from a HEX seed color.
 * If seedHex is null or invalid, falls back to default Material colors.
 */
@Composable
fun AppTheme(darkTheme: Boolean, seedHex: String?, content: @Composable () -> Unit) {
    val seedColor: Color? = seedHex?.let { parseHexToColor(it) }
    val base = seedColor ?: Color(0xFF6750A4) // fallback seed
    val scheme = generateColorScheme(base, darkTheme)

    MaterialTheme(colorScheme = scheme) {
        content()
    }
}
