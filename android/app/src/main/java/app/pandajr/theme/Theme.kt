package app.pandajr.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PandaPrimaryDark,
    onPrimary = Color(0xFF022C22),
    secondary = PandaSecondaryDark,
    onSecondary = Color(0xFF451A03),
    error = PandaAlertDark,
    onError = Color.White,
    background = PandaGroundDark,
    onBackground = PandaInkDark,
    surface = PandaSurfaceDark,
    onSurface = PandaInkDark,
    outline = PandaBorderDark
)

private val LightColorScheme = lightColorScheme(
    primary = PandaPrimaryLight,
    onPrimary = Color.White,
    secondary = PandaSecondaryLight,
    onSecondary = Color.White,
    error = PandaAlertLight,
    onError = Color.White,
    background = PandaGroundLight,
    onBackground = PandaInkLight,
    surface = PandaSurfaceLight,
    onSurface = PandaInkLight,
    outline = PandaBorderLight
)

@Composable
fun PandaJrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
