package com.riyas.cleanarchitecture.data.models

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("recipes")
    var recipes: List<Recipe>
)