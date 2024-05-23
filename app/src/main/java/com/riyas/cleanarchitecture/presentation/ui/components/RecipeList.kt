package com.riyas.cleanarchitecture.presentation.ui.components

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
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.riyas.cleanarchitecture.R
import com.riyas.cleanarchitecture.domain.models.Recipe
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RecipeList(recipes: List<Recipe>) {
   /* val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val scope = rememberCoroutineScope()
    val selectedData = mutableStateOf<Recipe>(recipes[0])
*/
    Scaffold(
        topBar = {
            TopAppBar(
                contentColor = Color.Black,
                backgroundColor = Color.White,
                title = {
                    Text(
                        "Home",
                        maxLines = 1,
                    )
                }
            )
        },
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalItemSpacing = 5.dp,
            modifier = Modifier.padding(it).fillMaxSize(),
            content = {
                items(recipes.size) { index ->
                    RecipeItem(recipes[index]) {
                        /*scope.launch {
                            selectedData.value = recipes[index]
                            bottomSheetState.expand()
                        }*/
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeItem(
    recipe: Recipe,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = {
            onClick()
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
                modifier = Modifier.height(150.dp).fillMaxWidth().clip(MaterialTheme.shapes.medium),
            )

            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = recipe.name,
                    maxLines = 2,
                    minLines = 2,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "Preparation Time: ${recipe.prepTimeMinutes} Mnts",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "Cooking Time: ${recipe.cookTimeMinutes} Mnts",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                )
                RecipeTags(recipe.tags)
            }
        }
    }
}