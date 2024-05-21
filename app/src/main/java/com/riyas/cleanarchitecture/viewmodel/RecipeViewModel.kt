package com.riyas.cleanarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyas.cleanarchitecture.data.model.ApiResponse
import com.riyas.cleanarchitecture.data.network.ApiStatus
import com.riyas.cleanarchitecture.data.usecases.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase
): ViewModel() {

    private val _recipeDataState: MutableStateFlow<RecipeDataState> = MutableStateFlow(RecipeDataState.Loading)
    val recipeDataState = _recipeDataState.asStateFlow()

    fun fetchRecipe() {
        viewModelScope.launch {
            try {
                recipeUseCase().collect{ response ->
                    when(response.status){
                        ApiStatus.LOADING-> {
                            _recipeDataState.value = RecipeDataState.Loading
                        }
                        ApiStatus.SUCCESS-> {
                            response.data?.let {
                                _recipeDataState.value =  RecipeDataState.Success(it)
                            }

                        }
                        ApiStatus.ERROR-> {
                            response.message?.let {
                                _recipeDataState.value =  RecipeDataState.Error(it)
                            }

                        }
                    }
                }
            } catch (e: Exception) {
                _recipeDataState.value =  RecipeDataState.Error("Failed to fetch data")
            }
        }
    }
}

sealed class RecipeDataState {
    data object Loading: RecipeDataState()
    data class Error(val errorMessage: String): RecipeDataState()
    data class Success(val responseData: ApiResponse): RecipeDataState()
}