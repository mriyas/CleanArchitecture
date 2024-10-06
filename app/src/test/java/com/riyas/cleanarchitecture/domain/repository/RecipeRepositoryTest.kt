package com.riyas.cleanarchitecture.domain.repository

import app.cash.turbine.test
import com.riyas.cleanarchitecture.data.models.RecipeDto
import com.riyas.cleanarchitecture.data.remote.RecipeRemoteSource
import com.riyas.cleanarchitecture.data.utils.NetWorkResult
import com.riyas.cleanarchitecture.expectedRecipeDto
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class RecipeRepositoryTest {

    private val recipeRemoteSource: RecipeRemoteSource = mock()
    private lateinit var recipeRepository: RecipeRepositoryImpl

    @Before
    fun setUp() {
        recipeRepository = RecipeRepositoryImpl(recipeRemoteSource)
    }

    @Test
    fun `Given remote source return data, When getRecipes invokes, Then should return success result with data`() = runTest {
        val successResult = NetWorkResult.Success(expectedRecipeDto)
        `when`(recipeRemoteSource.getRecipes()).thenReturn(flowOf(successResult))

        val result = recipeRepository.getRecipes()

        result.test {
            assertEquals(successResult, awaitItem())  // Check the success result
            awaitComplete()
        }
    }

    @Test
    fun `Given remote source return error, When getRecipes invokes, Then should return error result with data`() = runTest {
        val errorResult = NetWorkResult.Error<RecipeDto>(exception = "Error")
        `when`(recipeRemoteSource.getRecipes()).thenReturn(flowOf(errorResult))

        val result = recipeRepository.getRecipes()

        result.test {
            assertEquals(errorResult, awaitItem())  // Check the error result
            awaitComplete()
        }
    }
}
