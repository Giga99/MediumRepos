package com.medium.swipetorefreshplaceholder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val isRefreshing = viewModel.isRefreshing.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val currentTime = viewModel.currentTime.collectAsState().value
    val items = viewModel.items.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::refresh,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 32.dp,
                horizontal = 16.dp
            )
    ) {
        LazyColumn {
            item {
                Text(
                    text = "Welcome to Swipe-to-Refresh!",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = currentTime.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(items) {
                Item(
                    rowItem = it,
                    childModifier = Modifier.placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.fade(),
                    )
                )
            }
        }
    }
}

@Composable
fun Item(
    rowItem: RowItem,
    childModifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(rowItem.rowImage),
                contentDescription = rowItem.number.toString(),
                modifier = childModifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Number: ${rowItem.number}",
                modifier = childModifier.fillMaxWidth()
            )
        }
    }
}
