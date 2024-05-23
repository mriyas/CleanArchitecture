package com.riyas.cleanarchitecture.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyas.cleanarchitecture.data.utils.NetworkStatus
import com.riyas.cleanarchitecture.domain.models.Recipe
import com.riyas.cleanarchitecture.domain.usecases.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase
): ViewModel() {

    private val _recipeDataState: MutableStateFlow<RecipeDataState> = MutableStateFlow(
        RecipeDataState.Loading
    )
    val recipeDataState = _recipeDataState.asStateFlow()

    fun fetchRecipe() {
        viewModelScope.launch {
            try {
                recipeUseCase().collect{ response ->
                    when(response.status){
                        NetworkStatus.LOADING-> {
                            _recipeDataState.value = RecipeDataState.Loading
                        }
                        NetworkStatus.SUCCESS-> {
                            response.data?.let {
                                _recipeDataState.value = RecipeDataState.Success(it.recipes)
                            }
                        }
                        NetworkStatus.ERROR-> {
                            response.message?.let {
                                _recipeDataState.value = RecipeDataState.Error(it)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _recipeDataState.value = RecipeDataState.Error("Failed to fetch data")
            }
        }
    }
}

sealed class RecipeDataState {
    data object Loading: RecipeDataState()
    data class Error(val errorMessage: String): RecipeDataState()
    data class Success(val recipes: List<Recipe>): RecipeDataState()
}