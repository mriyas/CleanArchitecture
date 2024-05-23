package com.riyas.cleanarchitecture.domain.repositories

import com.riyas.cleanarchitecture.data.models.RecipeDto
import com.riyas.cleanarchitecture.data.utils.NetWorkResult
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<NetWorkResult<RecipeDto>>
}