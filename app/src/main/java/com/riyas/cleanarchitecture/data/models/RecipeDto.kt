package com.riyas.cleanarchitecture.data.models

import com.google.gson.annotations.SerializedName
import com.riyas.cleanarchitecture.domain.models.Recipe

data class RecipeDto(
    @SerializedName("recipes")
    var recipes: List<Recipe>
)