package com.riyas.cleanarchitecture.presentation.ui.screens

import AppTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.riyas.cleanarchitecture.R
import com.riyas.cleanarchitecture.data.models.Recipe
import com.riyas.cleanarchitecture.presentation.ui.components.BulletList
import com.riyas.cleanarchitecture.presentation.ui.components.RecipeTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(
    recipe: Recipe,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = recipe.name,
                        maxLines = 1,
                        style = AppTheme.typography.h2,
                        color = AppTheme.colors.primaryTextColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = AppTheme.colors.primaryTextColor
                        )
                    }
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
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 300.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn {
                item {
                    Box(contentAlignment = Alignment.TopEnd) {

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(recipe.image)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = stringResource(R.string.description),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium),
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = recipe.name,
                            style = AppTheme.typography.h2,
                            color =AppTheme.colors.primaryTextColor,
                        )
                        RecipeTags(recipe.tags)
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = "Preparation Time: ${recipe.prepTimeMinutes} Mnts",
                            style = AppTheme.typography.h3,
                            color =AppTheme.colors.primaryTextColor,
                        )
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = "Cooking Time: ${recipe.cookTimeMinutes} Mnts",
                            style = AppTheme.typography.h3,
                            color =AppTheme.colors.primaryTextColor,
                        )
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = "Level: ${recipe.difficulty}",
                            style = AppTheme.typography.h3,
                            color =AppTheme.colors.information,
                        )

                        BulletList(
                            title = "Ingredients",
                            items = recipe.ingredients,
                            lineSpacing = 8.dp,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        BulletList(
                            title = "Instructions",
                            items = recipe.instructions,
                            lineSpacing = 8.dp,
                        )
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
    }
}

