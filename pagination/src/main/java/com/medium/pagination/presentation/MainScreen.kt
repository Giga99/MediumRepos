package com.medium.pagination.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.medium.pagination.domain.RecipeModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val recipes = mainViewModel.recipes.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Scroll for more recipes!",
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        when (val state = recipes.loadState.prepend) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
        when (val state = recipes.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
        items(
            items = recipes,
            key = { it.id }
        ) {
            it?.let { RecipeRow(recipeModel = it) }
        }
        when (val state = recipes.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
    }
}

@Composable
private fun RecipeRow(
    recipeModel: RecipeModel
) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(recipeModel.thumbnailUrl),
                contentDescription = recipeModel.thumbnailAltText,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = recipeModel.name,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recipeModel.getRating(),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

private fun LazyListScope.Loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

private fun LazyListScope.Error(
    message: String
) {
    item {
        Text(
            text = message,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.error
        )
    }
}
