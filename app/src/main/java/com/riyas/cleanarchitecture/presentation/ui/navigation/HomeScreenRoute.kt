package com.riyas.cleanarchitecture.presentation.ui.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

internal const val ArgumentRecipe = "recipe"

private const val HomeScreenRoute = "home"
private const val DetailsScreenRoute = "$HomeScreenRoute/details/{$ArgumentRecipe}"

sealed class NavigationRoute(val route: String) {
    data object Home : NavigationRoute(HomeScreenRoute)
    data object DetailsScreen : NavigationRoute(DetailsScreenRoute) {
        fun withRecipe(recipe: String) =
            route.replace("{$ArgumentRecipe}", URLEncoder.encode(recipe, StandardCharsets.UTF_8.toString()))
    }
}