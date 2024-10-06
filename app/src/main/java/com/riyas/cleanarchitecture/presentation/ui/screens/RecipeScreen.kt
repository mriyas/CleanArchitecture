package com.riyas.cleanarchitecture.presentation.ui.screens

import AppTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.riyas.cleanarchitecture.data.models.Recipe
import com.riyas.cleanarchitecture.presentation.ui.components.ProgressIndicator
import com.riyas.cleanarchitecture.presentation.ui.components.RecipeList
import com.riyas.cleanarchitecture.presentation.viewmodels.RecipeDataState
import com.riyas.cleanarchitecture.presentation.viewmodels.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    viewModel: RecipeViewModel = hiltViewModel(),
    onItemClick: (Recipe) -> Unit,
) {
    val recipeScreenState by viewModel.recipeDataState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchRecipe()
    }
    androidx.compose.material3.Scaffold(
        topBar = {
           TopAppBar(
                title = {
                    Text(
                        text = "Yummy Recipes",
                        maxLines = 1,
                        style = AppTheme.typography.h2,
                        color = AppTheme.colors.primaryTextColor
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colors.background,
                    titleContentColor = AppTheme.colors.primaryTextColor,
                    navigationIconContentColor = AppTheme.colors.primaryTextColor
                )
            )
        },
        containerColor = AppTheme.colors.background,
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when (recipeScreenState) {
                is RecipeDataState.Idle,
                is RecipeDataState.Loading -> {
                    ProgressIndicator()
                }

                is RecipeDataState.Success -> {
                    val recipes = (recipeScreenState as RecipeDataState.Success).recipes
                    RecipeList(recipes, onItemClick)
                }

                is RecipeDataState.Error -> {
                    //show Error
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (recipeScreenState as RecipeDataState.Error).errorMessage,
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.error,
                            color = AppTheme.colors.error,
                        )
                    }
                }
            }
        }
    }
}

