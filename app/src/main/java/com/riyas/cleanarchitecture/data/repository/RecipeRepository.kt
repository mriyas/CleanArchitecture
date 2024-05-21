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