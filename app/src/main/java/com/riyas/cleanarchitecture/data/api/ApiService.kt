package com.riyas.cleanarchitecture.data.api

import com.riyas.cleanarchitecture.data.model.ApiResponse
import com.riyas.cleanarchitecture.data.model.User
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("recipes")
    suspend fun getRecipes(): ApiResponse
}