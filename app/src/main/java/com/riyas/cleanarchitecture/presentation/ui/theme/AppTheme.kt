import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import com.riyas.cleanarchitecture.presentation.ui.theme.AppTypography
import com.riyas.cleanarchitecture.presentation.ui.theme.Colors
import com.riyas.cleanarchitecture.presentation.ui.theme.darkColorSchema
import com.riyas.cleanarchitecture.presentation.ui.theme.lightColorSchema
import com.riyas.cleanarchitecture.presentation.ui.theme.appTypography

@Composable
fun AppTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppColors provides if(isDarkTheme) darkColorSchema else lightColorSchema,
        LocalAppTypography provides appTypography,
    ) {
        content()
    }
}

object AppTheme {
    val colors: Colors
        @ReadOnlyComposable
        @Composable
        get() = LocalAppColors.current

    val typography: AppTypography
        @ReadOnlyComposable
        @Composable
        get() = LocalAppTypography.current
}

internal val LocalAppColors =  staticCompositionLocalOf<Colors> { error("Color were not set") }
internal val LocalAppTypography =  staticCompositionLocalOf<AppTypography> { error("AppTypography were not set") }


@Preview(
    name = "DarkTheme",
    showBackground = false,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "LightTheme",
    showBackground = false,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
internal annotation class MultipleThemePreview