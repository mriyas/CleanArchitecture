package com.riyas.cleanarchitecture.presentation.ui.components

import AppTheme
import MultipleThemePreview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.riyas.cleanarchitecture.R
import com.riyas.cleanarchitecture.data.models.Recipe

@Composable
internal fun RecipeList(
    recipes: List<Recipe>,
    onItemClick: (Recipe) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalItemSpacing = 5.dp,
        modifier = Modifier
            .fillMaxSize(),
        content = {
            items(recipes.size) { index ->
                RecipeItem(recipes[index]) {
                    onItemClick(it)
                }
            }
        }
    )
}

@Composable
fun RecipeItem(
    recipe: Recipe,
    onClick: (Recipe) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background
        ),
        onClick = {
            onClick(recipe)
        },
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
            )

            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = recipe.name,
                    maxLines = 2,
                    minLines = 2,
                    fontWeight = FontWeight.Bold,
                    style = AppTheme.typography.h2,
                    color = AppTheme.colors.primaryTextColor,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "Preparation Time: ${recipe.prepTimeMinutes} Mnts",
                    fontWeight = FontWeight.Normal,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.secondaryTextColor,
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "Cooking Time: ${recipe.cookTimeMinutes} Mnts",
                    fontWeight = FontWeight.Normal,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.secondaryTextColor,
                )
                RecipeTags(recipe.tags)
            }
        }
    }
}

@Composable
@MultipleThemePreview
private fun RecipeList_Preview() {
    val recipes: List<Recipe> = List(100) { Recipe(name = "Recipe $it", tags = arrayListOf("tag1", "tag2")) }
    AppTheme(isDarkTheme = isSystemInDarkTheme()) {
        RecipeList(recipes = recipes) {

        }
    }
}