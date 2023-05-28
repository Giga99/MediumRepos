package com.medium.liststickyheaders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lazy list with sticky headers",
            style = MaterialTheme.typography.headlineMedium
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            repeat(5) {
                stickyHeader {
                    Text(
                        text = "Header ${it + 1}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF464549))
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(10) { index ->
                    Text(
                        text = "Item $index",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    )
                }
            }
        }
    }
}
