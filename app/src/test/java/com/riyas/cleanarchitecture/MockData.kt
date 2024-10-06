package com.riyas.cleanarchitecture

import com.riyas.cleanarchitecture.data.models.Recipe
import com.riyas.cleanarchitecture.data.models.RecipeDto

val expectedRecipes = arrayListOf(
    Recipe(
        name = "Test Recipe 1"
    ),
    Recipe(
        name = "Test Recipe 2"
    )
)

val expectedRecipeDto = RecipeDto(
    recipes = expectedRecipes
)