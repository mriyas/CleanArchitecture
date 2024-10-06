package com.riyas.cleanarchitecture.data.remote

import app.cash.turbine.test
import com.riyas.cleanarchitecture.data.models.RecipeDto
import com.riyas.cleanarchitecture.data.utils.NetWorkResult
import com.riyas.cleanarchitecture.data.utils.NetworkUtils
import com.riyas.cleanarchitecture.expectedRecipeDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class RecipeRemoteSourceTest {

    private val apiService: ApiService = mock()
    private val networkUtils: NetworkUtils = mock()
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var recipeRemoteSource: RecipeRemoteSource

    @Before
    fun setup() {
        recipeRemoteSource = RecipeRemoteSource(apiService, networkUtils, testDispatcher)
    }

    @Test
    fun `Given phone have internet connectivity, When getRecipes call invokes, Then a successful result should return`() = runTest {

        `when`(networkUtils.isOnline()).thenReturn(true)
        `when`(apiService.getRecipes()).thenReturn(expectedRecipeDto)

        recipeRemoteSource.getRecipes().test {
            assertEquals(awaitItem(), NetWorkResult.Loading) // First item should be loading
            assertEquals(awaitItem(), NetWorkResult.Success(expectedRecipeDto)) // Next, we expect success
            awaitComplete() // Make sure the flow completes
        }
    }

    @Test
    fun `Given phone have NO internet connectivity, When getRecipes call invokes, Then an error result should return`() = runTest {
        // Arrange
        `when`(networkUtils.isOnline()).thenReturn(false)

        recipeRemoteSource.getRecipes().test {
            assertEquals(awaitItem(), NetWorkResult.Loading)
            assertEquals(awaitItem(), NetWorkResult.Error<RecipeDto>(exception = "No connectivity"))
            awaitComplete()
        }
    }

    @Test
    fun `Given phone have internet connectivity, When getRecipes call throws an exception, Then an error result should return`() = runTest {
        `when`(networkUtils.isOnline()).thenReturn(true)
        `when`(apiService.getRecipes()).thenThrow(RuntimeException("API error"))

        recipeRemoteSource.getRecipes().test {
            assertEquals(awaitItem(), NetWorkResult.Loading)
            assertTrue(awaitItem() is NetWorkResult.Error)
            awaitComplete()
        }
    }
}
