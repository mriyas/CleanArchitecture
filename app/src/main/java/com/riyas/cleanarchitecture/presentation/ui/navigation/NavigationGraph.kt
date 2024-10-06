package com.riyas.cleanarchitecture.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.riyas.cleanarchitecture.data.models.Recipe
import com.riyas.cleanarchitecture.presentation.ui.screens.RecipeDetailsScreen
import com.riyas.cleanarchitecture.presentation.ui.screens.RecipeScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val gson = Gson()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home.route
    ) {
        composable(NavigationRoute.Home.route) {
            RecipeScreen {
                val itemJson = gson.toJson(it)
                navController.navigate(NavigationRoute.DetailsScreen.withRecipe(itemJson))
            }
        }
        composable(
            route = NavigationRoute.DetailsScreen.route,
            arguments = listOf(
                navArgument(ArgumentRecipe) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val recipeJson = backStackEntry.arguments?.getString(ArgumentRecipe)
            val recipe = recipeJson?.let { gson.fromJson(it, Recipe::class.java) }
            if (recipe != null) {
                RecipeDetailsScreen(
                    recipe = recipe,
                ) {
                    navController.navigateUp()
                }
            }
        }
    }
}
