package com.riyas.cleanarchitecture.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyas.cleanarchitecture.data.models.Recipe
import com.riyas.cleanarchitecture.data.utils.NetWorkResult
import com.riyas.cleanarchitecture.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _recipeDataState: MutableStateFlow<RecipeDataState> = MutableStateFlow(
        RecipeDataState.Idle
    )
    val recipeDataState = _recipeDataState.asStateFlow()

    fun fetchRecipe() {
        viewModelScope.launch {
            try {
                repository.getRecipes().collect { response ->
                    when (response) {
                        is NetWorkResult.Loading -> {
                            println("Setting Loading State")
                            _recipeDataState.value = RecipeDataState.Loading
                        }

                        is NetWorkResult.Success -> {
                            println("Setting Success State")
                            response.data?.let {
                                _recipeDataState.value = RecipeDataState.Success(it.recipes)
                            }
                        }

                        is NetWorkResult.Error -> {
                            println("Setting Error State")
                            response.message?.let {
                                _recipeDataState.value = RecipeDataState.Error(it)
                            }
                        }

                        is NetWorkResult.Idle -> {}
                    }
                }
            } catch (e: Exception) {
                _recipeDataState.value = RecipeDataState.Error("Failed to fetch data")
            }
        }
    }
}

sealed class RecipeDataState {
    data object Idle : RecipeDataState()
    data object Loading : RecipeDataState()
    data class Error(val errorMessage: String) : RecipeDataState()
    data class Success(val recipes: List<Recipe>) : RecipeDataState()
}