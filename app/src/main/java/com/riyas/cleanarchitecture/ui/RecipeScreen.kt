package com.riyas.cleanarchitecture.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.riyas.cleanarchitecture.ui.components.ProgressIndicator
import com.riyas.cleanarchitecture.ui.components.RecipeList
import com.riyas.cleanarchitecture.viewmodel.RecipeDataState
import com.riyas.cleanarchitecture.viewmodel.RecipeViewModel

@Composable
fun RecipeScreen(viewModel: RecipeViewModel = hiltViewModel()){

    val recipeScreenState by viewModel.recipeDataState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRecipe()
    }

    when (recipeScreenState) {
        is RecipeDataState.Loading -> {
            ProgressIndicator()
        }
        is RecipeDataState.Success -> {
            val products = (recipeScreenState as RecipeDataState.Success).responseData.list
            RecipeList(products)
        }
        is RecipeDataState.Error -> {
           //show Error
        }
    }
}

