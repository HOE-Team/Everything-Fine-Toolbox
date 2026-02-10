package theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private fun clamp01(v: Float) = when {
    v < 0f -> 0f
    v > 1f -> 1f
    else -> v
}

fun isValidHex(hex: String): Boolean {
    return Regex("^#?[0-9a-fA-F]{6}$").matches(hex)
}

fun parseHexToColor(hex: String): Color? {
    val cleaned = hex.removePrefix("#")
    if (!isValidHex(cleaned)) return null
    val intVal = cleaned.toLong(16).toInt()
    val r = (intVal shr 16) and 0xFF
    val g = (intVal shr 8) and 0xFF
    val b = intVal and 0xFF
    return Color(r, g, b)
}

fun adjustLuminance(color: Color, factor: Float): Color {
    val r = clamp01(color.red * factor)
    val g = clamp01(color.green * factor)
    val b = clamp01(color.blue * factor)
    return Color(r, g, b, color.alpha)
}

fun contrastColor(color: Color): Color {
    val lum = 0.2126f * color.red + 0.7152f * color.green + 0.0722f * color.blue
    return if (lum < 0.5f) Color(0xFFFFFFFF) else Color(0xFF000000)
}

fun generateColorScheme(seed: Color, dark: Boolean) = if (dark) {
    darkColorScheme(
        primary = seed,
        onPrimary = contrastColor(seed),
        secondary = adjustLuminance(seed, 0.85f),
        onSecondary = contrastColor(adjustLuminance(seed, 0.85f)),
        background = Color(0xFF121212),
        onBackground = Color(0xFFFFFFFF),
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFFFFFFFF)
    )
} else {
    lightColorScheme(
        primary = seed,
        onPrimary = contrastColor(seed),
        secondary = adjustLuminance(seed, 0.9f),
        onSecondary = contrastColor(adjustLuminance(seed, 0.9f)),
        background = Color(0xFFFFFFFF),
        onBackground = Color(0xFF000000),
        surface = Color(0xFFFFFFFF),
        onSurface = Color(0xFF000000)
    )
}
