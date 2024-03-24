package com.example.dualingo_clone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


@Immutable
data class AppColors(
    val materialColors: ColorScheme,
){
    val primary = materialColors.primary
    val secondary = materialColors.secondary
    val splash = materialColors.background
    val button = materialColors.onPrimary
    val userlistitem = materialColors.primaryContainer
}

val darkAppColors = AppColors(
    materialColors = darkColorScheme(
        primary = Black,
        secondary = BlackyBlue,
        background = SplashColor,
        onPrimary = ButtonColor,
        primaryContainer = Graye,
    )
)

val lightAppColors = lightColorScheme(
    primary = SplashColor,
    secondary = White,
    background = SplashColor,
    primaryContainer = Graye,
    onPrimary = ButtonColor,
)

@Composable
fun Dualingo_cloneTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (isDarkTheme: Boolean) -> Unit
) {
    val appColors = if (isDarkTheme){
        darkAppColors
    } else {
        lightAppColors
    }
    val localAppColors = staticCompositionLocalOf { appColors }

    CompositionLocalProvider (localAppColors provides appColors) {
        MaterialTheme(
            colorScheme = darkAppColors.materialColors,
            shapes = appShapes,
            typography = appTypography,
            content = {
                content(isDarkTheme)
            }
        )
    }
}

