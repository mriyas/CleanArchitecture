package com.riyas.cleanarchitecture.data.repository

import com.riyas.cleanarchitecture.data.api.ApiService
import com.riyas.cleanarchitecture.data.model.ApiResponse
import com.riyas.cleanarchitecture.data.network.toResultFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import com.riyas.cleanarchitecture.data.network.NetWorkResult
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    fun getRecipes(): Flow<NetWorkResult<ApiResponse>> {
        return toResultFlow {
            withContext(dispatcher){
                val response = apiService.getRecipes()
                NetWorkResult.Success(response)
            }
        }
    }
}

class RecipeUseCase(
    private val recipeRepository: RecipeRepository,
) {
    // This method doesn't need to worry about moving the execution of the
    // coroutine to a different thread as newsRepository is main-safe.
    // The work done in the coroutine is lightweight as it only creates
    // a list and add elements to it
    suspend operator fun invoke(): Flow<NetWorkResult<ApiResponse>> {
        return recipeRepository.getRecipes()
    }
}