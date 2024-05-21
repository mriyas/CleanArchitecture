package com.riyas.cleanarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyas.cleanarchitecture.data.model.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.riyas.cleanarchitecture.data.network.ApiStatus
import com.riyas.cleanarchitecture.repository.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase
): ViewModel() {

    private val _recipeState = MutableStateFlow(HomeState())
    private val _homeViewState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState.Loading)
    val homeViewState = _homeViewState.asStateFlow()

    fun getProducts() {
        viewModelScope.launch {
            try {
                recipeUseCase().collect{ response ->
                    when(response.status){
                        ApiStatus.LOADING->{
                            _recipeState.update { it.copy(isLoading = true) }
                        }
                        ApiStatus.SUCCESS->{
                            _recipeState.update { it.copy(isLoading = false, responseData = response.data) }
                        }
                        ApiStatus.ERROR->{
                            _recipeState.update { it.copy(isLoading = false,errorMessage = response.message) }
                        }
                    }
                    _homeViewState.value = _recipeState.value.toUiState()
                }
            } catch (e: Exception) {
                _recipeState.update { it.copy(isLoading = false,errorMessage ="Failed to fetch data") }
            }
        }
    }
    sealed class HomeScreenState {
        data object Loading: HomeScreenState()
        data class Error(val errorMessage: String): HomeScreenState()
        data class Success(val responseData: ApiResponse): HomeScreenState()
    }

    private data class HomeState(
        val isLoading:Boolean = false,
        val errorMessage: String? = null,
        val responseData: ApiResponse? = null
    ) {
        fun toUiState(): HomeScreenState {
            return if (isLoading) {
                HomeScreenState.Loading
            } else if(errorMessage?.isNotEmpty()==true) {
                HomeScreenState.Error(errorMessage)
            } else {
                HomeScreenState.Success(responseData!!)
            }
        }
    }
}