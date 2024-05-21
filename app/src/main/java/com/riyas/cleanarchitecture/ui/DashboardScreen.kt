package com.riyas.cleanarchitecture.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.riyas.cleanarchitecture.ui.components.ProgressIndicator
import com.riyas.cleanarchitecture.ui.components.RecipeList
import com.riyas.cleanarchitecture.viewmodel.RecipeViewModel

@Composable
fun DashboardScreen(viewModel: RecipeViewModel = hiltViewModel()){

    val homeScreenState by viewModel.homeViewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    when (homeScreenState) {
        is RecipeViewModel.HomeScreenState.Loading -> {
            ProgressIndicator()
        }
        is RecipeViewModel.HomeScreenState.Success -> {
            val products = (homeScreenState as RecipeViewModel.HomeScreenState.Success).responseData.list
            RecipeList(products)
        }
        is RecipeViewModel.HomeScreenState.Error -> {
           //show Error
        }

        else -> {
            //no-op
        }
    }
}

