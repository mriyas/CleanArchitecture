package com.riyas.cleanarchitecture.data.usecases

import com.riyas.cleanarchitecture.data.model.ApiResponse
import com.riyas.cleanarchitecture.data.network.NetWorkResult
import com.riyas.cleanarchitecture.data.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

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