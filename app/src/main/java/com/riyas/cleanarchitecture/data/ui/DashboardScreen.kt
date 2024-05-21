package com.riyas.cleanarchitecture.data.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.riyas.cleanarchitecture.data.ui.components.RecipeList
import com.riyas.cleanarchitecture.data.viewmodel.DashboardViewModel
import ui.ProgressIndicator

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()){

    val homeScreenState by viewModel.homeViewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }
    when (homeScreenState) {
        is DashboardViewModel.HomeScreenState.Loading -> {
            ProgressIndicator()
        }
        is DashboardViewModel.HomeScreenState.Success -> {
            val products = (homeScreenState as DashboardViewModel.HomeScreenState.Success).responseData.list
            RecipeList(products)
        }
        is DashboardViewModel.HomeScreenState.Error -> {
           //show Error
        }
    }
}

