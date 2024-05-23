package com.riyas.cleanarchitecture.data.remote

import com.riyas.cleanarchitecture.data.models.RecipeDto
import retrofit2.http.GET

interface ApiService {


    @GET("recipes")
    suspend fun getRecipes(): RecipeDto
}