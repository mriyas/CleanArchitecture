package com.riyas.cleanarchitecture.data.remote

import com.riyas.cleanarchitecture.data.models.RecipeDto
import com.riyas.cleanarchitecture.data.utils.toResultFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import com.riyas.cleanarchitecture.data.utils.NetWorkResult
import com.riyas.cleanarchitecture.data.utils.NetworkUtils
import javax.inject.Inject

class RecipeRemoteSource @Inject constructor(
    private val apiService: ApiService,
    private val networkUtils: NetworkUtils,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    fun getRecipes(): Flow<NetWorkResult<RecipeDto>> {
        return toResultFlow(networkUtils.isOnline()) {
            withContext(dispatcher) {
                val response = apiService.getRecipes()
                NetWorkResult.Success(response)
            }
        }
    }
}