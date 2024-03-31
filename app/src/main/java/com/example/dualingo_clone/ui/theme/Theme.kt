package com.example.dualingo_clone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
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


@Immutable
data class AppColors(
    val materialColors: ColorScheme,
) {
    val primary = materialColors.primary
    val secondary = materialColors.secondary
    val splash = materialColors.background
    val button = materialColors.onPrimary
    val userListItem = materialColors.primaryContainer
    val boldText = materialColors.onSecondary
    val text = materialColors.onTertiary
    val activeDot = materialColors.onError
    val activeLanguage = materialColors.onError
    val inactiveLanguage = materialColors.onErrorContainer
    val field = materialColors.onSecondaryContainer
    val forgotPassword = materialColors.outline
}

val darkAppColors = AppColors(
    materialColors = darkColorScheme(
        primary = Black,
        secondary = BlackyBlue,
        background = SplashColor,
        onPrimary = ButtonColor,
        primaryContainer = Graye,
        onSecondary = White,
        onTertiary = BlackyGray,
        onError = Orange,
        onErrorContainer = Whity,
        onSecondaryContainer = FieldGray,
        outline = Pink,
    )
)

val lightAppColors = AppColors(
    materialColors = lightColorScheme(
        primary = SplashColor,
        secondary = White,
        background = SplashColor,
        primaryContainer = Graye,
        onPrimary = ButtonColor,
        onSecondary = Black,
        onTertiary = Graye,
        onError = Orange,
        onErrorContainer = Whity,
        onSecondaryContainer = FieldGray,
        outline = Pink,
    )
)

val localAppColors = staticCompositionLocalOf { lightAppColors }

@Composable
fun Dualingo_cloneTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (isDarkTheme: Boolean) -> Unit
) {
    val appColors = if (isDarkTheme) {
        darkAppColors
    } else {
        lightAppColors
    }
    val localAppColors = staticCompositionLocalOf { appColors }
    CompositionLocalProvider(localAppColors provides appColors) {
        MaterialTheme(
            colorScheme = appColors.materialColors,
            shapes = appShapes,
            typography = appTypography,
            content = {
                content(isDarkTheme)
            }
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = localAppColors.current

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography
}


