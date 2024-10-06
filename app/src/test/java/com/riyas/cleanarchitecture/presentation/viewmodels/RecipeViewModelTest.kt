package com.riyas.cleanarchitecture.presentation.viewmodels

import app.cash.turbine.test
import com.riyas.cleanarchitecture.data.models.RecipeDto
import com.riyas.cleanarchitecture.data.utils.NetWorkResult
import com.riyas.cleanarchitecture.domain.repository.RecipeRepository
import com.riyas.cleanarchitecture.expectedRecipeDto
import com.riyas.cleanarchitecture.expectedRecipes
import com.riyas.cleanarchitecture.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class RecipeViewModelTest {
    private val recipeRepository: RecipeRepository =  mock()
    private lateinit var viewModel: RecipeViewModel

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(UnconfinedTestDispatcher())

    @Before
    fun setUp() {
        viewModel = RecipeViewModel(recipeRepository)
    }

    @Test
    fun `Given fetchRecipe is invoked, it should emit Loading and Success states`() = runTest {
        val successResult = NetWorkResult.Success(expectedRecipeDto)

        // Mock the use case to emit Loading and then Success
        `when`(recipeRepository.getRecipes()).thenReturn(
            flow {
                emit(NetWorkResult.Loading)
                delay(100)  // Simulate some delay
                emit(successResult)
            }
        )

        viewModel.recipeDataState.test {
            viewModel.fetchRecipe()

            // Assert the first emission is Idle
            val idleState = awaitItem()
            println("Idle State Emitted: $idleState")
            assertEquals(RecipeDataState.Idle, idleState)

            // Assert the first emission is loading
            val loadingState = awaitItem()
            println("Loading State Emitted: $loadingState")
            assertEquals(RecipeDataState.Loading, loadingState)

            // Assert the third emission is Success
            val successState = awaitItem()
            println("Success State Emitted: $successState")
            assertEquals(RecipeDataState.Success(expectedRecipes), successState)
        }
    }

    @Test
    fun `Given connected to network, When fetchRecipe invokes and encountered an error, Then should emit Loading then Error state`() =
        runTest {
            val errorMessage = "Error fetching data"
            val errorResult = NetWorkResult.Error<RecipeDto>(exception = errorMessage)

            // Mock the use case to emit Loading and then error
            `when`(recipeRepository.getRecipes()).thenReturn(
                flow {
                    emit(NetWorkResult.Loading)
                    delay(100)  // Simulate some delay
                    emit(errorResult)
                }
            )

            viewModel.recipeDataState.test {
                viewModel.fetchRecipe()

                // Assert the first emission is Idle
                val idleState = awaitItem()
                assertEquals(RecipeDataState.Idle, idleState)

                // Assert the first emission is loading
                val loadingState = awaitItem()
                println("Loading State Emitted: $loadingState")
                assertEquals(RecipeDataState.Loading, loadingState)

                // Assert the third emission is Error
                val errorState = awaitItem()
                println("Error State Emitted: $errorState")
                assertEquals(RecipeDataState.Error(errorMessage = errorMessage), errorState)
            }
        }
}