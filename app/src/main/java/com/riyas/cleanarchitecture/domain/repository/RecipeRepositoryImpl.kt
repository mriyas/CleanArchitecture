package com.riyas.cleanarchitecture.domain.repository

import com.riyas.cleanarchitecture.data.models.RecipeDto
import com.riyas.cleanarchitecture.data.utils.NetWorkResult
import com.riyas.cleanarchitecture.data.remote.RecipeRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeRemoteSource: RecipeRemoteSource,
): RecipeRepository {
    override fun getRecipes(): Flow<NetWorkResult<RecipeDto>> {
        return recipeRemoteSource.getRecipes()
    }
}