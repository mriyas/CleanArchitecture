package com.riyas.cleanarchitecture.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.riyas.cleanarchitecture.presentation.ui.components.ProgressIndicator
import com.riyas.cleanarchitecture.presentation.ui.components.RecipeList
import com.riyas.cleanarchitecture.presentation.viewmodels.RecipeDataState
import com.riyas.cleanarchitecture.presentation.viewmodels.RecipeViewModel

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
            val recipes = (recipeScreenState as RecipeDataState.Success).recipes
            RecipeList(recipes)
        }
        is RecipeDataState.Error -> {
           //show Error
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (recipeScreenState as RecipeDataState.Error).errorMessage,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

